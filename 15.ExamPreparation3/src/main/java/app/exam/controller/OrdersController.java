package app.exam.controller;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.xml.OrderWrapperXMLImportDTO;
import app.exam.parser.ValidationUtil;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class OrdersController {

    private OrderService orderService;
    private Parser parser;
    private Parser jsonParser;
    @Autowired
    public OrdersController(OrderService orderService,
                            @Qualifier("XMLParser") Parser parser,
                            @Qualifier("JSONParser") Parser jsonParser) {
        this.orderService = orderService;
        this.parser = parser;
        this.jsonParser = jsonParser;
    }

    public String importDataFromXML(String xmlContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        OrderWrapperXMLImportDTO orders = parser.read(OrderWrapperXMLImportDTO.class, xmlContent);

        orders.getOrders().forEach(a -> {
            if (ValidationUtil.isValid(a)) {
                try {
                    this.orderService.create(a);
                    sb.append(String.format("Order for %s on %s added.",a.getCustomer(),a.getDate())).append(System.lineSeparator());
                } catch (ParseException ignored) {
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            } else {
                sb.append("Error: Invalid data.").append(System.lineSeparator());
            }

        });
        return sb.toString();
    }

    public String exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) throws IOException, JAXBException {
        EmployeeOrdersJSONExportDTO employeeOrdersJSONExportDTO = this.orderService.exportOrdersByEmployeeAndOrderType(employeeName, orderType);
        return this.jsonParser.write(employeeOrdersJSONExportDTO);
    }
}
