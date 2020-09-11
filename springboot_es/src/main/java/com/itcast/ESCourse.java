package com.itcast;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author YXD
 * @date 2020/4/13
 */
@Data
@Document(indexName = "escourse")
public class ESCourse {


    @Id
    private String id;
    @Field(type = FieldType.Text)
    private Integer num;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String type;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String introduct;
    @Field(type = FieldType.Text)
    private String img;
}
