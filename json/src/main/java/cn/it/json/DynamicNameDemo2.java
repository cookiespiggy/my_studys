package cn.it.json;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DynamicNameDemo2 {
    public static void main(String[] args) throws IOException {
        String json = "{\"id21\":88}";
        ObjectMapper mapper = new ObjectMapper();
        Data data = mapper.readValue(json, Data.class);
        System.out.println(mapper.writeValueAsString(data));
    }


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
