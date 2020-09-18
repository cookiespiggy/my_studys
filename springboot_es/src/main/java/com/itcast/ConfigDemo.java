package com.itcast;

import com.amazonaws.annotation.Beta;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration

public class ConfigDemo {

    @Component
    @ConfigurationProperties(prefix = "es")
    static class ConfigBean {
//        @NestedConfigurationProperty
        String host;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            System.out.println("-------------------------------wo bei diaoyongle ");
            this.host = host;
        }
    }

}
