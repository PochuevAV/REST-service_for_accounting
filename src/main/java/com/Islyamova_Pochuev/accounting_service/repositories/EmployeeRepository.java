package com.Islyamova_Pochuev.accounting_service.repositories;

import com.Islyamova_Pochuev.accounting_service.employees.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
