//package cn.it.json;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.io.IOException;
//
//public class DynamicNameDemo5 {
//    public static void main(String[] args) throws IOException {
//        String json = "{\"id21\":33,\"name\":\"xiaomi\"}";
//        ObjectMapper mapper = new ObjectMapper();
//        Data data = mapper.readValue(json, Data.class);
//        System.out.println(mapper.writeValueAsString(data));
//    }
//
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    static class Data {
//        @JsonSerialize(using = PojoSerializer.class)
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
//    static class PojoSerializer extends JsonSerializer<Integer> {
//
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
