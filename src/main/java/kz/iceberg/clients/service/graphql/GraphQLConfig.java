package kz.iceberg.clients.service.graphql;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava.tracing.FederatedTracingInstrumentation;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.GraphQlSource;

import java.util.List;

@Configuration
public class GraphQLConfig {
    @Autowired
    private ClientsResolver clientsResolver;

    @Bean
    public GraphQLSchema getGraphQLSchema() {
        String federationTypeName = "Client";
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withBasePackages("kz.iceberg.clients.service.entity")
                .withOperationsFromSingletons(clientsResolver)
                .generate();
        // TODO add apollo federation from https://github.com/setchy/graphql-java-kickstart-federation-example/tree/master
        return schema;
    }

    @Bean
    @Autowired
    public GraphQL getGraphQL(GraphQLSchema graphQLSchema) {
        return GraphQL
                .newGraphQL(graphQLSchema)
                .schema(graphQLSchema)
                .queryExecutionStrategy(new AsyncExecutionStrategy())
                .instrumentation(new FederatedTracingInstrumentation())
                .build();
    }

//    @Bean
//    @Autowired
//    public GraphQL getGraphQL(GraphQlSource graphQlSource) {
//        return graphQlSource.graphQl();
//    }

    @Bean
    @Autowired
    GraphQlSource getGraphQLSource(GraphQLSchema graphQLSchema) {
        return GraphQlSource
                .builder(graphQLSchema)
                .instrumentation(List.of(
                        new FederatedTracingInstrumentation(),
                        new ChainedInstrumentation()))
                .build();
    }

    @Bean
    public GraphQlSourceBuilderCustomizer federationTransform() {
        return builder -> builder.schemaFactory(
                (registry, wiring) ->
                        Federation.transform(registry, wiring)
                                .fetchEntities(env -> null)
                                .resolveEntityType(env -> null)
                                .build()
        );
    }

    @Bean
    public GraphQlProperties.Graphiql getGraphiQL() {
        GraphQlProperties.Graphiql graphiql = new GraphQlProperties.Graphiql();
        graphiql.setEnabled(true);
        graphiql.setPath("/graphiql");
        return graphiql;
    }
}
