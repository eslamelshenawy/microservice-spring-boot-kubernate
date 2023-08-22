package com.example.dbservice.repository;

import com.example.dbservice.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	List<Employee> findByOrganizationId(Long organizationId);
	
}
