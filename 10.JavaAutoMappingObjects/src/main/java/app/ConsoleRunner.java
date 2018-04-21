package app;

import app.dtos.EmployeeDto;
import app.dtos.ManagerDto;
import app.entities.Address;
import app.entities.Employee;
import app.repositories.AddressRepository;
import app.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class ConsoleRunner implements CommandLineRunner {

    private AddressRepository addressRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public ConsoleRunner(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Employee employee2 = employeeRepository.findOne(2L);
        Employee employee1= employeeRepository.findOne(1L);
//
       // employee2.setBirthDate(LocalDate.parse("1976-01-01"));
       // employeeRepository.save(employee2);
       // employee1.setBirthDate( LocalDate.parse("1988-01-01"));
       // employeeRepository.save(employee1);
       ModelMapper mapper = new ModelMapper();
//
      //  EmployeeDto dto = mapper.map(employee,EmployeeDto.class);
      //  ManagerDto
      //  System.out.println(dto.getFirstName());

       // Employee employee1 = new Employee();
       // employee1.setSaraly(new BigDecimal(800));
       // employee1.setFirstName("Pesho");
       // employee1.setLastName("Dimitrov");
     //  employeeRepository.save(employee1);
       // Set<Employee> employees = new HashSet<Employee>();
       // employees.add(employee1);
       // employee.setEmployees(employees);

      //  employeeRepository.save(employee);
      //  ManagerDto managerDto = mapper.map(employee1,ManagerDto.class);

     //   for (EmployeeDto employeeDto : managerDto.getEmployees()) {
     //       System.out.println(employeeDto.getFirstName());
     //   }

        List<Employee> employees = this.employeeRepository.findAllByBirthDateBeforeOrderBySaralyDesc(LocalDate.parse("1990-01-01"));
        for (Employee employee : employees) {
            EmployeeDto employeeDto = mapper.map(employee,EmployeeDto.class);
            System.out.printf("%s %s %s – Manager: %s\n",employeeDto.getFirstName(),
                    employeeDto.getLastName(),
                    employeeDto.getSalary(),
                    employeeDto.getManager() == null? "[no manager]":employeeDto.getManager().getLastName());
        }

    }
}
