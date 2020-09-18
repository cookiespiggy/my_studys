package cn.it.json;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DynamicNameDemo3 {
    public static void main(String[] args) throws IOException {
        String json = "{\"id21\":88}";
        ObjectMapper mapper = new ObjectMapper();
        Data data = mapper.readValue(json, Data.class);
        System.out.println(mapper.writeValueAsString(data));
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Data {
        @JsonAlias({"id1","id2","id"})
        private Integer id;
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
    }

}
