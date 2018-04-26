package app.exam.service.impl;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.json.OrderJSONExportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.domain.entities.*;
import app.exam.parser.interfaces.ModelParser;
import app.exam.parser.interfaces.Parser;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderRepository;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ModelParser parser;
    private EmployeeRepository employeeRepository;
    private ItemsRepository itemsRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ModelParser parser,
                            EmployeeRepository employeeRepository,
                            ItemsRepository itemsRepository) {
        this.orderRepository = orderRepository;
        this.parser = parser;
        this.employeeRepository = employeeRepository;
        this.itemsRepository = itemsRepository;
    }

    @Override
    public void create(OrderXMLImportDTO dto) throws ParseException {
        Employee employee = employeeRepository.findOneByName(dto.getEmployee());

        for (Item item : dto.getItems()) {
            if (itemsRepository.findByName(item.getName()) == null) {
                throw new IllegalArgumentException("Item not present in database.");
            }
        }
        Order order = new Order();
        order.setCustomer(dto.getCustomer());
        order.setOrderType(OrderType.valueOf(dto.getType()));
        order.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dto.getDate()));
        employee.getOrders().add(order);
        order.setEmployee(employee);
        orderRepository.saveAndFlush(order);

    }

    @Override
    public EmployeeOrdersJSONExportDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        Employee employee = this.employeeRepository.findOneByName(employeeName);
        List<Order> orderList = employee.getOrders().stream().filter(x -> x.getOrderType().name().equals(orderType)).collect(Collectors.toList());

        for (Order order : orderList) {
            calculateTotalPrice(order);
        }
        sortByPrice(orderList);
        EmployeeOrdersJSONExportDTO employeeDto = new EmployeeOrdersJSONExportDTO();
        employeeDto.setEmployeeName(employeeName);
        List<OrderJSONExportDTO> orderListDto = mapOrderJsonExportDtoItems(orderList);
        employeeDto.setOrders(orderListDto);
        return employeeDto;
    }

    private List<OrderJSONExportDTO> mapOrderJsonExportDtoItems(List<Order> orderList) {
        List<OrderJSONExportDTO> orderExportDto = new ArrayList<>();
        for (Order order : orderList) {
            OrderJSONExportDTO dto = new OrderJSONExportDTO();
            dto.setCustomer(order.getCustomer());
            ItemJSONExportDTO[] itemDtos = new ItemJSONExportDTO[order.getOrderItems().size()];
            int count = 0;
            for (OrderItem orderItem : order.getOrderItems()) {
                ItemJSONExportDTO itemDto = new ItemJSONExportDTO();
                itemDto.setName(orderItem.getItem().getName());
                itemDto.setPrice(orderItem.getItem().getPrice());
                itemDto.setQuantity(orderItem.getQuantity());
                itemDtos[count] = itemDto;
                count++;
            }
            dto.setItems(itemDtos);
            orderExportDto.add(dto);
        }
        return orderExportDto;

    }

    private void sortByPrice(List<Order> items) {
        Collections.sort(items, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                int equality = o2.getTotalPrice().compareTo(o1.getTotalPrice());
                int itemsCountEquality = Integer.compare(o2.getOrderItems().size(), o1.getOrderItems().size());
                return equality != 0 ? equality : itemsCountEquality;
            }
        });
    }

    private void calculateTotalPrice(Order order) {
        BigDecimal total = new BigDecimal(0);
        for (OrderItem orderItem : order.getOrderItems()) {
                Item item = orderItem.getItem();
                int quantity = orderItem.getQuantity();
                BigDecimal price = item.getPrice().multiply(BigDecimal.valueOf(quantity));
                total = total.add(price);
        }
        order.setTotalPrice(total);
    }
}
