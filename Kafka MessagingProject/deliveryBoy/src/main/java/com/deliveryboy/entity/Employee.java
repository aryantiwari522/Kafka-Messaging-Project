package com.deliveryboy.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "emp_dtl", schema = "emp_res")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	
	@Column(name = "emp_id")
	private long id;

	@Column(name = "emp_nm")
	private String name;

	@Column(name = "emp_sal")
	private double salary;

	@Column(name = "emp_adr")
	private String address;
	
	@Column(name= "emp_joinDt")
	@CreationTimestamp
	private Date joiningDate;

}
