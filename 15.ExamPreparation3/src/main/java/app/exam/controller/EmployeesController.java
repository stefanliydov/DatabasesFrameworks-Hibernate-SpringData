package app.exam.controller;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.parser.ValidationUtil;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

@Controller
public class EmployeesController {
    private EmployeeService employeeService;
    private final Parser parser;

    @Autowired
    public EmployeesController(EmployeeService employeeService,
                               @Qualifier("JSONParser") Parser parser) {
        this.employeeService = employeeService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb=  new StringBuilder();
        EmployeeJSONImportDTO[] animals = parser.read(EmployeeJSONImportDTO[].class,jsonContent);
        Arrays.stream(animals).forEach(a->{

            if(ValidationUtil.isValid(a)) {
                    this.employeeService.create(a);
                sb.append(String.format("Record %s successfully imported.", a.getName())).append(System.lineSeparator());
            }else {
                sb.append("Error: Invalid data.").append(System.lineSeparator());
            }
        });


        return sb.toString();
    }
}
