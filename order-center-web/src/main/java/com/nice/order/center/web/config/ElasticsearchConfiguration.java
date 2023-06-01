//package com.nice.order.center.web.config;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
// 研究下
// logging.level.org.springframework.data.elasticsearch.client.WIRE:trace
//@Configuration
//public class ElasticsearchConfiguration extends AbstractElasticsearchConfiguration {
//
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("ip:port")
//                .build();
//
//        RestHighLevelClient restHighLevelClient = RestClients.create(clientConfiguration).rest();
//        return restHighLevelClient;
//    }
//
//    @Bean(name = {"elasticsearchOperations", "elasticsearchTemplate"})
//    @Override
//    public ElasticsearchOperations elasticsearchOperations() {
//        return new ElasticsearchRestTemplate(elasticsearchClient(), elasticsearchConverter(), resultsMapper());
//    }
//}
