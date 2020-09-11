package com.itcast;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * https://github.com/wuneng-class/wuneng
 */
public interface ESCourseDao extends ElasticsearchRepository<ESCourse, String> {
    @Query("{\"match\" : {\"type\" : \"?0\"}}}")
    List<ESCourse> listESCourse(String type);
}
