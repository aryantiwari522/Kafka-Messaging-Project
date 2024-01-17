package com.deliveryboy.repository;

import com.deliveryboy.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // You can add custom query methods here if needed
}
