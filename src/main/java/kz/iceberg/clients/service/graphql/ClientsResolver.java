package kz.iceberg.clients.service.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import kz.iceberg.clients.service.ClientService;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.graphql.input.PaginationEntry;
import kz.iceberg.clients.service.graphql.input.SortEntry;
import kz.iceberg.clients.service.wrapper.FilterWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsResolver {
    @Autowired
    private ClientService service;

    @GraphQLQuery(name = "clients")
    public Optional<ClientEntity> getById(@GraphQLArgument(name = "id") Long id) {
        return service.retrieve(id);
    }

    @GraphQLQuery(name = "clientsList")
    public Optional<List<ClientEntity>> getList(@GraphQLArgument(name = "search") String search,
                                                @GraphQLArgument(name = "pagination") PaginationEntry pagination,
                                                @GraphQLArgument(name = "sort") SortEntry sort) {
        var filter = new FilterWrapper();
        filter.setSearch(search);
        filter.setPagination(pagination);
        filter.setSort(sort);
        return service.list(filter);
    }


    @GraphQLQuery(name = "updateClient")
    public Optional<ClientEntity> update(@GraphQLArgument(name = "client") ClientEntity entity) {
        return Optional.ofNullable(service.update(entity));
    }

    @GraphQLQuery(name = "deleteClient")
    public Optional<Boolean> delete(@GraphQLArgument(name = "id") Long id) {
        service.delete(id);
        return Optional.of(true);
    }

    @GraphQLQuery(name = "createClient")
    public Optional<ClientEntity> create(@GraphQLArgument(name = "client") ClientEntity entity) {
        return service.add(entity);
    }
}
