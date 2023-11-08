package kz.iceberg.clients.service.graphql.federation;

import graphql.introspection.Introspection;
import io.leangen.graphql.annotations.types.GraphQLDirective;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@GraphQLDirective(name = "external", repeatable = true, locations = Introspection.DirectiveLocation.FIELD, description = "Can be used for GraphQL Federation")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface GraphQLFederationExternal {
}