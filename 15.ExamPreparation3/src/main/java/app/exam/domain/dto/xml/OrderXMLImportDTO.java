package app.exam.domain.dto.xml;

import app.exam.domain.entities.Item;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXMLImportDTO{

    @XmlElement(name = "customer")
    private String customer;
    @XmlElement(name = "employee")
    @Size(min = 3, max = 30)
    private String employee;
    @XmlElement(name = "date")
    // @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private String date;
    @XmlElement(name = "type")
    private String type;
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<Item> items;

    public OrderXMLImportDTO() {
        this.items = new ArrayList<>();
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return this.employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
