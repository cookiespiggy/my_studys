package com.itcast;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages = "com.itcast")
public class App implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Resource
    private ConfigDemo.ConfigBean bean;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(bean.getHost());
    }
}
