package app.exam.terminal;

import app.exam.config.Config;
import app.exam.controller.EmployeesController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.domain.entities.Employee;
import app.exam.io.interfaces.ConsoleIO;
import app.exam.io.interfaces.FileIO;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
@Transactional
public class Terminal implements CommandLineRunner {
    private EmployeesController employeesController;
    private FileIO fileIO;
    private ConsoleIO consoleIO;
    private PositionRepository positionRepository;
    private ItemsController itemsController;
    private OrdersController ordersController;
    private EmployeeRepository employeeRepository;

    @Autowired
    public Terminal(EmployeesController employeesController, FileIO fileIO, ConsoleIO consoleIO, PositionRepository positionRepository, ItemsController itemsController, OrdersController ordersController, EmployeeRepository employeeRepository) {
        this.employeesController = employeesController;
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.positionRepository = positionRepository;
        this.itemsController = itemsController;
        this.ordersController = ordersController;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        consoleIO.write(employeesController.importDataFromJSON(fileIO.read(Config.EMPLOYEES_IMPORT_JSON)));
        //consoleIO.write(itemsController.importDataFromJSON(fileIO.read(Config.ITEMS_IMPORT_JSON)));
        //consoleIO.write(ordersController.importDataFromXML(fileIO.read(Config.ORDERS_IMPORT_XML)));
        this.consoleIO.write(this.ordersController.exportOrdersByEmployeeAndOrderType("Avery Rush", "ToGo"));
        Employee avery_rush = this.employeeRepository.findOneByName("Avery Rush");
        String debug="";
    }
}
