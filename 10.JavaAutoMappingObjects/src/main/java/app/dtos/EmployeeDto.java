package app.dtos;


import app.entities.Address;

import java.math.BigDecimal;

public class EmployeeDto extends NamedDto{



    private BigDecimal saraly;

    private Address address;

    private EmployeeDto manager;

    public BigDecimal getSalary() {
        return this.saraly;
    }

    public void setSalary(BigDecimal salary) {
        this.saraly = salary;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public EmployeeDto getManager() {
        return this.manager;
    }

    public void setManager(EmployeeDto manager) {
        this.manager = manager;
    }
}
