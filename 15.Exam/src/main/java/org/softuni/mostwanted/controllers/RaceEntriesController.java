package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.xml.RaceEntriesWrapperXMLImportDTO;
import org.softuni.mostwanted.entities.dto.xml.RaceEntriesXMLImportDTO;
import org.softuni.mostwanted.entities.models.Car;
import org.softuni.mostwanted.entities.models.Racer;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RaceEntriesRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.RaceEntriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceEntriesController {
    private RaceEntriesService raceEntriesService;
    private RacerRepository racerRepository;
    private CarRepository carRepository;
    private RaceEntriesRepository raceEntriesRepository;
    private Parser parser;

    @Autowired
    public RaceEntriesController(RaceEntriesService raceEntriesService, RacerRepository racerRepository, CarRepository carRepository, RaceEntriesRepository raceEntriesRepository, @Qualifier("XMLParser") Parser parser) {
        this.raceEntriesService = raceEntriesService;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.raceEntriesRepository = raceEntriesRepository;
        this.parser = parser;

    }

    public String raceEntriesXMLImport(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        RaceEntriesWrapperXMLImportDTO raceEntries = parser.read(RaceEntriesWrapperXMLImportDTO.class, jsonContent);
        int count = 1;
        for (RaceEntriesXMLImportDTO a : raceEntries.getRaceEntries()) {
            if (ValidationUtil.isValid(a)) {
                try {
                    if (!a.getRacer().isEmpty()) {
                        Racer racer = racerRepository.findOneByName(a.getRacer());
                        Car car;
                        if(a.getCarId()!=null) {
                             car = carRepository.findOne(a.getCarId());
                        }else{
                          car = new Car();
                        }
                        if (racer == null || car == null) {
                            sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                        } else {
                            this.raceEntriesService.create(a);
                            sb.append(String.format("Successfully imported RaceEntry - %s.",
                                    count)).append(System.lineSeparator());
                            count++;
                        }
                    } else {
                        this.raceEntriesService.create(a);
                        sb.append(String.format("Successfully imported RaceEntry  - %s.",
                                count)).append(System.lineSeparator());
                        count++;
                    }
                } catch (IllegalArgumentException error) {
                    sb.append("Error: Duplicate Data!").append(System.lineSeparator());
                }
            } else {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
            }
        }


        return sb.toString();
    }
}
