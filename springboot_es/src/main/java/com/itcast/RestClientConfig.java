package com.itcast;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.http.HttpHeaders;

import java.time.Duration;

/*

https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.clients.rest
 */
@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {

//        //使用HttpHeaders设置相关的请求头信息
//        HttpHeaders defaultHeaders = new HttpHeaders();
//
//
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .withConnectTimeout(Duration.ofSeconds(5))
//                .withSocketTimeout(Duration.ofSeconds(3))
//                .withDefaultHeaders(defaultHeaders)
//                .build();
//
//        return RestClients.create(clientConfiguration).rest();

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                //.withBasicAuth("app_read","HfmLuoAZwxSr")
                .withDefaultHeaders(HttpHeaders.EMPTY)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

}
