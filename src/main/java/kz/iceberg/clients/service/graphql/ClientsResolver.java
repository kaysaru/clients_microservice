package kz.iceberg.clients.service.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import kz.iceberg.clients.service.ClientService;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.graphql.input.MapEntryInputAdapter;
import kz.iceberg.clients.service.wrapper.FilterWrapper;
import kz.iceberg.clients.service.wrapper.enums.Ascension;
import lombok.Getter;
import lombok.Setter;
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
    public Optional<List<ClientEntity>> getList(@GraphQLArgument(name = "search") String search,
                                                @GraphQLArgument(name = "pagination") MapEntryInputAdapter<Integer, Integer> pagination,
                                                @GraphQLArgument(name = "sort") MapEntryInputAdapter<String, Ascension> sort) {
        var filter = new FilterWrapper();
        filter.setSearch(search);
        filter.setPagination(MapEntryInputAdapter.toEntry(pagination.getKey(), pagination.getValue()));
        filter.setSort(MapEntryInputAdapter.toEntry(sort.getKey(), sort.getValue()));
        return service.list(filter);
    }
}
