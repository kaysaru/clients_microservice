package kz.iceberg.clients.service.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import kz.iceberg.clients.service.service.ClientService;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.entity.dto.ClientEntityDto;
import kz.iceberg.clients.service.graphql.input.PaginationEntry;
import kz.iceberg.clients.service.graphql.input.SortEntry;
import kz.iceberg.clients.service.service.MergeService;
import kz.iceberg.clients.service.wrapper.FilterWrapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsResolver {
    private final ClientService clientService;

    ClientsResolver( ClientService clientService) {
        this.clientService = clientService;
    }

    @GraphQLQuery(name = "clients")
    public Optional<ClientEntity> getById(@GraphQLArgument(name = "id") Long id) {
        return clientService.retrieve(id);
    }

    @GraphQLQuery(name = "clientsList")
    public Optional<List<ClientEntity>> getList(@GraphQLArgument(name = "search") String search,
                                                @GraphQLArgument(name = "pagination") PaginationEntry pagination,
                                                @GraphQLArgument(name = "sort") SortEntry sort) {
        var filter = new FilterWrapper();
        filter.setSearch(search);
        filter.setPagination(pagination);
        filter.setSort(sort);
        return clientService.list(filter);
    }


    @GraphQLMutation(name = "updateClient")
    public Optional<ClientEntity> update(@GraphQLArgument(name = "client") ClientEntityDto entity) {
        System.out.println(entity);
        return clientService.update(entity);
    }

    @GraphQLMutation(name = "deleteClient")
    public Optional<Boolean> delete(@GraphQLArgument(name = "id") Long id) {
        clientService.delete(id);
        return Optional.of(true);
    }

    @GraphQLMutation(name = "createClient")
    public Optional<ClientEntity> create(@GraphQLArgument(name = "client") ClientEntity entity) {
        return clientService.add(entity);
    }
}
