package com.enduser.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.deliveryboy.entity.Employee;
import com.deliveryboy.repository.EmployeeRepository;

@Configuration
public class KafkaConfig {
	

	 @Autowired
	    private EmployeeRepository employeeRepository;
	 
	 @Value("${spring.kafka.consumer.bootstrap-servers}")
	    private String bootstrapServers;

	    @Bean
	    public ConsumerFactory<String, Employee> consumerFactory() {
	        Map<String, Object> config = new HashMap<>();
	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	        config.put(ConsumerConfig.GROUP_ID_CONFIG, AppConstants.GROUP_ID);
	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); // Use JsonDeserializer for Employee class
	        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Employee.class));
	    }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, Employee> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, Employee> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory());
	        return factory;
	    }
	    
	    @KafkaListener(topics = AppConstants.LOCATION_UPDATE_TOPIC, groupId = AppConstants.GROUP_ID)
	    public void updatedLocation(Employee employee) {
	        // Process the received Employee object (save it in the repository, log, etc.)
	        System.out.println("Received Employee: " + employee.toString());
	        employeeRepository.save(employee);
	    }

	    
}
