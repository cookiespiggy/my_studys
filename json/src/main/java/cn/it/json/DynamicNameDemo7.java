//package cn.it.json;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//
//import java.io.IOException;
//
//public class DynamicNameDemo7 {
//    public static void main(String[] args) throws IOException {
//        String json = "{\"status\":88}";
//        ObjectMapper mapper = new ObjectMapper();
//        Response response = mapper.readValue(json, Response.class);
//        System.out.println(mapper.writeValueAsString(response));
//    }
//
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    static class Response {
//        @JsonSerialize(using = CustomSerializer.class)
//        private String status;
//        private String error;
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public String getError() {
//            return error;
//        }
//
//        public void setError(String error) {
//            this.error = error;
//        }
//    }
//
//    static class CustomSerializer extends JsonSerializer<Integer> {
//        public void serialize(Integer value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
//            jgen.writeStartObject();
//            jgen.writeObjectField(value.getClass().getName(), value);
//            jgen.writeEndObject();
//        }
//    }
//}
