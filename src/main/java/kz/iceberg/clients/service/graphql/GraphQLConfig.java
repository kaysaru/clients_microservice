package kz.iceberg.clients.service.graphql;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava.tracing.FederatedTracingInstrumentation;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

//    private final CandidateResolver candidateResolver;
//    private final ResumeResolver resumeResolver;
//
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
    public GraphQL getGraphQL(@Autowired GraphQLSchema graphQLSchema) {
        return GraphQL.newGraphQL(graphQLSchema)
                .queryExecutionStrategy(new AsyncExecutionStrategy())
                .instrumentation(new FederatedTracingInstrumentation())
                .build();
    }

    @Bean
    public GraphQlSourceBuilderCustomizer federationTransform() {
        return builder -> builder.schemaFactory((registry, wiring)->
                Federation.transform(registry, wiring)
                        .fetchEntities(env -> null)
                        .resolveEntityType(env -> null)
                        .build()
        );
    }
}
