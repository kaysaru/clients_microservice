package kz.iceberg.clients.service.graphql.federation;


import graphql.introspection.Introspection;
import io.leangen.graphql.annotations.types.GraphQLDirective;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@GraphQLDirective(name = "key", repeatable = true, locations = {Introspection.DirectiveLocation.OBJECT, Introspection.DirectiveLocation.INTERFACE}, description = "Can be used for GraphQL Federation")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GraphQLFederationKey {
    public String fields() default "id";
    public boolean resolvable = true;
}