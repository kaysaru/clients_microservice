package kz.iceberg.clients.service.graphql;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import kz.iceberg.clients.service.entity.ClientEntity;
import kz.iceberg.clients.service.entity.TimelineEntity;
import kz.iceberg.clients.service.service.ClientService;
import kz.iceberg.clients.service.service.MergeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MergeResolver {
    private final ClientService clientService;
    private final MergeService mergeService;

    MergeResolver(ClientService clientService, MergeService mergeService) {
        this.clientService = clientService;
        this.mergeService = mergeService;
    }

    @GraphQLMutation(name = "merge")
    public Optional<ClientEntity> merge(@GraphQLArgument(name = "target") Long targetId, @GraphQLArgument(name = "sources") List<Long> sourcesId) {
        mergeService.fetchAndMerge(targetId, sourcesId);
        return clientService.retrieve(targetId);
    }

    @GraphQLQuery(name = "timelineList")
    public Optional<List<TimelineEntity>> timelineList() {
        return Optional.ofNullable(mergeService.list());
    }
}
