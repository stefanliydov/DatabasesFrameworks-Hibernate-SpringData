package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.json.RacerJSONImportDTO;
import org.softuni.mostwanted.entities.dto.json.RacingCarsJSONExport;
import org.softuni.mostwanted.entities.models.Car;
import org.softuni.mostwanted.entities.models.Racer;
import org.softuni.mostwanted.entities.models.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RacerController {


    private Parser parser;
    private TownRepository townRepository;
    private RacerService racerService;
    private RacerRepository racerRepository;
    private CarRepository carRepository;

    @Autowired
    public RacerController(@Qualifier("JSONParser") Parser parser, TownRepository townRepository, RacerService racerService, RacerRepository racerRepository, CarRepository carRepository) {
        this.parser = parser;
        this.townRepository = townRepository;
        this.racerService = racerService;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
    }

    public String racerJSONImport(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        RacerJSONImportDTO[] racers = parser.read(RacerJSONImportDTO[].class, jsonContent);
        Arrays.stream(racers).forEach(a -> {
            if (ValidationUtil.isValid(a)) {
                try {
                    if (!a.getTownName().isEmpty()) {
                        Town town = townRepository.findOneByName(a.getTownName());
                        if (town == null) {
                            sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                        } else {
                            this.racerService.create(a);
                            sb.append(String.format("Successfully imported Racer - %s.", a.getName())).append(System.lineSeparator());
                        }
                    } else {
                        this.racerService.create(a);
                        sb.append(String.format("Successfully imported Racer - %s.", a.getName())).append(System.lineSeparator());
                    }
                } catch (IllegalArgumentException error) {
                    sb.append("Error: Duplicate Data!").append(System.lineSeparator());
                }
            } else {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
            }
        });


        return sb.toString();
    }

    public String racerExportJSON() throws IOException, JAXBException {
        List<Racer> allRacers = this.racerRepository.findAll();
        List<Racer> withCars = allRacers.stream().filter(a -> a.getCars().size() > 0).collect(Collectors.toList());
        List<RacingCarsJSONExport> dtos = new ArrayList<>();
        for (Racer racer : withCars) {
            RacingCarsJSONExport dto = new RacingCarsJSONExport();
            List<Car> allByRacer = carRepository.findAllByRacer(racer);
            dto.setName(racer.getName());
            dto.setAge(racer.getAge());
            List<String> cars =allByRacer
                    .stream().map(a-> a.getBrand()+" "+a.getModel()+" "+a.getYearOfProduction()).collect(Collectors.toList());
            dto.setCars(cars);
            dtos.add(dto);
        }
        List<RacingCarsJSONExport> collect = dtos.stream().sorted((a, b) -> {
            Integer aR = a.getCars().size();
            Integer bR = b.getCars().size();
            if (aR.equals(bR)) {
                return a.getName().compareTo(b.getName());
            }
            return bR - aR;
        }).collect(Collectors.toList());
        return this.parser.write(collect);
    }
}
