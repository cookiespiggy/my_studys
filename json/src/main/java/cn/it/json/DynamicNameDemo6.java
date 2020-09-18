package cn.it.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

public class DynamicNameDemo6 {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Response r1 = new Response("Error", "Some error", 20);
        System.out.println(mapper.writeValueAsString(r1));
        Response r2 = new Response("Error", "Some error", "some string");
        System.out.println(mapper.writeValueAsString(r2));
    }


    static class Response {
        private String status;
        private String error;
        //        @JsonProperty("p")
        @JsonSerialize(using = CustomSerializer.class)
        private Object data;

        public Response(String status, String error, Object data) {
            this.status = status;
            this.error = error;
            this.data = data;
        }
    }

    static class CustomSerializer extends JsonSerializer<Object> {
        public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
            jgen.writeStartObject();
            jgen.writeObjectField(value.getClass().getName(), value);
            jgen.writeEndObject();
        }
    }
}
