package kz.iceberg.clients.service.graphql;

import graphql.ExecutionInput;
import graphql.GraphQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class GraphQLController {

    private final GraphQL graphQL;
    private final Logger logger = LoggerFactory.getLogger(GraphQLController.class);

    @Autowired
    GraphQLController(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @PostMapping(path = "graphql",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> graphql(@RequestBody EntryPoint entryPoint) {


        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(entryPoint.query)
                .build();

        return graphQL.execute(executionInput).toSpecification();
    }

    public static class EntryPoint {
        public String query;
    }
}