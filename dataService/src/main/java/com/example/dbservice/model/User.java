package com.example.dbservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employee")
@Entity
public class Employee {

	@Id
	private Long id;
	private Long organizationId;
	private String name;
	private int age;
	private String position;

}
