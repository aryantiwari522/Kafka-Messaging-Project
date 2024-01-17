package com.deliveryboy.config;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.deliveryboy.entity.Employee;

@Configuration
public class KafkaConfig {
	
	@Bean
	public NewTopic topic() {
		
		return TopicBuilder
				.name(AppConstants.LOCATION_TOPIC_NAME)
				/*.partitions()*/
				/* .replicas() */
				.build();
	}
	
	
	 @Value("${spring.kafka.bootstrap-servers}")
	    private String bootstrapServers;

	    @Bean
	    public ProducerFactory<String, Employee> producerFactory() {
	        Map<String, Object> configProps = new HashMap<>();
	        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	        // Add other producer configurations

	        return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), new JsonSerializer<>());
	    }

	    @Bean
	    public KafkaTemplate<String, Employee> kafkaTemplate() {
	        return new KafkaTemplate<>(producerFactory());
	    }
	/*
	 * @Bean public KafkaTemplate<String, Employee>
	 * kafkaTemplate(ProducerFactory<String, Employee> producerFactory) { return new
	 * KafkaTemplate<>(producerFactory); }
	 */
	

}
