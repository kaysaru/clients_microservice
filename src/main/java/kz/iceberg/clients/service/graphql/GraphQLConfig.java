package kz.iceberg.clients.service.graphql;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.apollographql.federation.graphqljava.tracing.FederatedTracingInstrumentation;
import graphql.GraphQL;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;
import org.springframework.graphql.execution.GraphQlSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class GraphQLConfig {
    @Autowired
    private ClientsResolver clientsResolver;
    @Autowired
    private MergeResolver mergeResolver;

    @Bean
    public GraphQLSchema getGraphQLSchema() {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withBasePackages("kz.iceberg.clients.service.entity")
                .withOperationsFromSingletons(clientsResolver, mergeResolver)
                .generate();
        // TODO add apollo federation from https://github.com/setchy/graphql-java-kickstart-federation-example/tree/master
//        return schema;

//        SchemaParser parser = new SchemaParser();
//        TypeDefinitionRegistry typeDefinitionRegistry = parser.parse(Paths.get("schema.graphqls").toFile());
//        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring().build();

        return Federation.transform(schema, false).fetchEntities(env ->
                env.<List<Map<String, Object>>>getArgument(_Entity.argumentName).stream().map(reference -> {
                    if ((reference.get("__typename")).getClass().getSimpleName().startsWith("Client")) {
                        try {
                            Class<?> myClass = Class.forName(reference.get("__typename").getClass().getSimpleName());
                            return myClass.newInstance();// Can just pass main entity which contain all other entities
                        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return null;
                }).collect(Collectors.toList()))
                .resolveEntityType(new ClassNameTypeResolver())
                .build();
    }

    @Bean
    public GraphQL getGraphQL(GraphQlSource graphQlSource) {
        return graphQlSource.graphQl();
    }

    @Bean
    @Autowired
    GraphQlSource getGraphQLSource(GraphQLSchema graphQLSchema) {
        return GraphQlSource.builder(graphQLSchema).instrumentation(List.of(new ChainedInstrumentation())).build();
    }

    @Bean
    public GraphQlSourceBuilderCustomizer federationTransform() {
        return GraphQlSource.Builder::build;
    }

    /**
     * Enables GraphiQL on /graphiql
     */
    @Bean
    public GraphQlProperties.Graphiql getGraphiQL() {
        GraphQlProperties.Graphiql graphiql = new GraphQlProperties.Graphiql();
        graphiql.setEnabled(true);
        graphiql.setPath("/graphiql");
        return graphiql;
    }
}
