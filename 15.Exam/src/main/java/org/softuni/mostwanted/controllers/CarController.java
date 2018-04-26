package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.json.CarJSONImportDTO;
import org.softuni.mostwanted.entities.models.Racer;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class CarController {

    private CarService carService;
    private RacerRepository racerRepository;
    private Parser parser;

    @Autowired
    public CarController(CarService carService, RacerRepository racerRepository, @Qualifier("JSONParser") Parser parser) {
        this.carService = carService;
        this.racerRepository = racerRepository;
        this.parser = parser;

    }

    public String carJSONImport(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb=  new StringBuilder();
        CarJSONImportDTO[] cars = parser.read(CarJSONImportDTO[].class,jsonContent);
        Arrays.stream(cars).forEach(a->{
            if(ValidationUtil.isValid(a)) {
                try {
                    if (!a.getRacerName().isEmpty()){
                        Racer racer = racerRepository.findOneByName(a.getRacerName());
                        if(racer==null){
                            sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                        }else{
                            this.carService.create(a);
                            sb.append(String.format("Successfully imported Car - %s %s @ %s", a.getBrand(),
                                    a.getModel(),
                                    a.getYearOfProduction())).append(System.lineSeparator());
                        }
                    }else {
                        this.carService.create(a);
                        sb.append(String.format("Successfully imported Car - %s %s @ %s", a.getBrand(),
                                a.getModel(),
                                a.getYearOfProduction())).append(System.lineSeparator());;
                    }
                }catch (IllegalArgumentException error){
                    sb.append("Error: Duplicate Data!").append(System.lineSeparator());
                }
            }else {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
            }
        });


        return sb.toString();
    }
}
