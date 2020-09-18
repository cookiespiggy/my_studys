//package cn.it.json;
//
//import com.fasterxml.jackson.annotation.JsonAlias;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//
//@SpringBootApplication
//public class DynamicNameDemo4 implements CommandLineRunner {
//    public static void main(String[] args) throws IOException {
//        SpringApplication.run(DynamicNameDemo4.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        String json = "{\"id21\":33,\"name\":\"xiaomi\"}";
//        ObjectMapper mapper = new ObjectMapper();
//        Data data = mapper.readValue(json, Data.class);
//        System.out.println(mapper.writeValueAsString(data));
//    }
//
//
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    static class Data {
//        @JsonSerialize(using = IntegerSerializer.class)
//        private Integer id;
//        private String name;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }
//
//    //    @Component
//    static class IntegerSerializer extends JsonSerializer<Integer> {
//
//        //        @Value("${xxx.id:99")
//        private String nameField = "id";
//
//        @Override
//        public void serialize(Integer value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeObjectField(nameField, value);
//            // https://stackoverflow.com/questions/35089257/conditional-jsonproperty-using-jackson-with-spring-boot
//            jsonGenerator.writeEndObject();
//        }
//    }
//
//}
