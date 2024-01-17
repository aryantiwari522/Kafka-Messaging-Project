package com.deliveryboy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.deliveryboy.config.AppConstants;
import com.deliveryboy.entity.Employee;
import com.deliveryboy.repository.EmployeeRepository;

@Service
public class KafkaService {
	
	@Autowired
	private KafkaTemplate<String,Employee> kafkaTemplate;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Logger logger=LoggerFactory.getLogger(KafkaService.class);
	
	public boolean updateLocation(Employee employee) {
		//employeeRepository.save(employee);
			kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME,employee);
			logger.info("Employee details produced: " + employee.toString());
			
			
		return true;
	}
}
