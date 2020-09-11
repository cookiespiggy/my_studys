package com.itcast;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

import java.util.List;

import static java.util.Collections.singletonMap;
import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

@SpringBootApplication
public class EsDbConnectorApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EsDbConnectorApp.class, args);
    }

    @Resource
    RestHighLevelClient highLevelClient;

    @Resource
    ESCourseDao esCourseDao;


    @Override
    public void run(String... args) throws Exception {

//        IndexRequest request = new IndexRequest("spring-data")
//                .source(singletonMap("feature", "high-level-rest-client"))
//                .setRefreshPolicy(IMMEDIATE);
//
//        IndexResponse response = highLevelClient.index(request, RequestOptions.DEFAULT);
//        System.out.println(response);

        //List<ESCourse> list = esCourseDao.listESCourse("语文");
        // System.out.println(list);

    }
}
