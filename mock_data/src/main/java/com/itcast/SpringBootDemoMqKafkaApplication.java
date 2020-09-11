package com.itcast;

import com.itcast.config.KafkaConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringBootDemoMqKafkaApplication implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoMqKafkaApplication.class, args);


    }


    public void testSend() throws InterruptedException {
        kafkaTemplate.send(KafkaConsts.TOPIC_TEST, "1,kafka");

        Thread.sleep(5_000);

        kafkaTemplate.send(KafkaConsts.TOPIC_TEST, "2,kafka");

        Thread.sleep(5_000);

        kafkaTemplate.send(KafkaConsts.TOPIC_TEST, "1,hadoop");
    }

    @Override
    public void run(String... args) throws Exception {
        testSend();
    }
}
