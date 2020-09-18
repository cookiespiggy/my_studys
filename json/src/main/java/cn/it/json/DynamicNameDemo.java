package cn.it.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DynamicNameDemo {
    public static void main(String[] args) throws IOException {
        String json = "{\"id\":88}";
        ObjectMapper mapper = new ObjectMapper();
        Data data = mapper.readValue(json, Data.class);
        System.out.println(mapper.writeValueAsString(data));
    }


    static class Data {
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @JsonAnySetter
        private Map<String, Object> unknownFields = new HashMap<>();

        public Map<String, Object> getUnknownFields() {
            return unknownFields;
        }

        public void setUnknownFields(Map<String, Object> unknownFields) {
            this.unknownFields = unknownFields;
        }
    }

}
