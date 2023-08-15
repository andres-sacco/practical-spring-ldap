package com.apress.book.ldap.repository;

import java.util.List;

import com.apress.book.ldap.domain.Employee;

public interface EmployeeDao {

	public void create(Employee employee);
	
	public void update(Employee employee);
	
	public void delete(String id);
	
	public Employee find(String id);
	
	public List<Employee> findAll();
}
