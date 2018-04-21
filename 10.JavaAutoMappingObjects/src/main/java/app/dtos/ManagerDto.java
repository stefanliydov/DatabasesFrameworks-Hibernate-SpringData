package app.dtos;

import java.util.Set;

public class ManagerDto extends NamedDto {

    private Set<EmployeeDto> employees;

    public Set<EmployeeDto> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<EmployeeDto> employees) {
        this.employees = employees;
    }
}
