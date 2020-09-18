//package cn.itcast.demo;
//
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Data;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class DynamicNameDemo {
//    public static void main(String[] args) throws JsonProcessingException {
//        String json = "{\"id\":888}";
//        ObjectMapper mapper = new ObjectMapper();
//        Data data = mapper.readValue(json, Data.class);
//        System.out.println(data);
//    }
//
//    static class Data {
//        @JsonAnySetter
//        private String unknownFields;
//    }
//
//}
