//package kz.iceberg.clients.service.graphql;
//
//import graphql.schema.DataFetcher;
//import kz.iceberg.clients.service.ClientService;
//import kz.iceberg.clients.service.entity.ClientEntity;
//import kz.iceberg.clients.service.repository.GQLClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.SpringVersion;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class GraphQLDataFetcher {
//
//    // Communicates with Application's Service Layer and Fetches data required by GraphQL
//
//    @Autowired
//    private ClientService clientService;
//    @Autowired
//    private GQLClientRepository clientRepository;
//
//    public DataFetcher<List<ClientEntity>> getLists() {
//        return dataFetchingEnvironment -> {
//            return clientRepository.findAll();
//        };
//    }
//
////    public DataFetcher<Long> getItemListId() {
////        return dataFetchingEnvironment -> {
////            Optional<Item> item = Optional.ofNullable(dataFetchingEnvironment.getSource());
////            if(item.isPresent()){
////                return item.get().getListEntity().getId();
////            }else{
////                return null;
////            }
////        };
////    }
//}