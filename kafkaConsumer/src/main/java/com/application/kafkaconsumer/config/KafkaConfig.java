package com.application.kafkaconsumer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:8090");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
        return args -> {
            template.send("topic1", "test");
        };
    }

//    @Bean
//    public RoutingKafkaTemplate routingTemplate(GenericApplicationContext context,
//                                                ProducerFactory<Object, Object> pf) {
//
//        // Clone the PF with a different Serializer, register with Spring for shutdown
//        Map<String, Object> configs = new HashMap<>(pf.getConfigurationProperties());
//        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
//        DefaultKafkaProducerFactory<Object, Object> bytesPF = new DefaultKafkaProducerFactory<>(configs);
//        context.registerBean(DefaultKafkaProducerFactory.class, "bytesPF", bytesPF);
//
//        Map<Pattern, ProducerFactory<Object, Object>> map = new LinkedHashMap<>();
//        map.put(Pattern.compile("two"), bytesPF);
//        map.put(Pattern.compile(".+"), pf); // Default PF with StringSerializer
//        return new RoutingKafkaTemplate(map);
//    }
//
//    @Bean
//    public ApplicationRunner runner(RoutingKafkaTemplate routingTemplate) {
//        return args -> {
//            routingTemplate.send("one", "thing1");
//            routingTemplate.send("two", "thing2".getBytes());
//        };
//    }
}
