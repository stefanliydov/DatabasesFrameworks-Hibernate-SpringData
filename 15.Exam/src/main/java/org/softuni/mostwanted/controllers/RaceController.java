package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.xml.RacesWrapperXMLImportDTO;
import org.softuni.mostwanted.entities.dto.xml.RacesXMLImportDTO;
import org.softuni.mostwanted.entities.models.Car;
import org.softuni.mostwanted.entities.models.District;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.repositories.*;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceController {


    private DistrictRepository districtRepository;

    private RaceService raceService;
    private RaceRepository raceRepository;
    private Parser parser;

    @Autowired
    public RaceController(DistrictRepository districtRepository,

                          RaceService raceService, RaceRepository raceRepository,@Qualifier("XMLParser") Parser parser) {
        this.districtRepository = districtRepository;
        this.raceService = raceService;
        this.raceRepository = raceRepository;
        this.parser = parser;
    }

    public String raceXMLImport(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        RacesWrapperXMLImportDTO raceEntries = parser.read(RacesWrapperXMLImportDTO.class, jsonContent);
        int count = 1;
        for (RacesXMLImportDTO a : raceEntries.getRaces()) {
            if (ValidationUtil.isValid(a)) {
                try {
                    if (a.getDistrictName()!=null) {
                        District racer = districtRepository.findOneByName(a.getDistrictName());
                        if (racer == null) {
                            sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                        } else {
                            this.raceService.create(a);
                            sb.append(String.format("Successfully imported Race - %s.",
                                    count)).append(System.lineSeparator());
                            count++;
                        }
                    } else {
                        this.raceService.create(a);
                        sb.append(String.format("Successfully imported Race  - %s.",
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
