package kz.iceberg.clients.service.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import kz.iceberg.clients.service.ClientService;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.wrapper.FilterWrapper;
import kz.iceberg.clients.service.wrapper.enums.Ascension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
    public Optional<List<ClientEntity>> getList(String search, Map.Entry<Integer, Integer> pagination,
                                                Map.Entry<String, Ascension> sort) {
        var filter = new FilterWrapper();
        filter.setSearch(search);
        filter.setPagination(pagination);
        filter.setSort(sort);
        return service.list(filter);
    }
}
