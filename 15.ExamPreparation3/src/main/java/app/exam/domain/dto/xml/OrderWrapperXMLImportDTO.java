package app.exam.domain.dto.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderWrapperXMLImportDTO {

    @XmlElement(name = "order")
    private List<OrderXMLImportDTO> orders;

    public List<OrderXMLImportDTO> getOrders() {
        return this.orders;
    }

    public void setOrders(List<OrderXMLImportDTO> orders) {
        this.orders = orders;
    }
}
