package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.json.TownJSONImportDTO;
import org.softuni.mostwanted.entities.dto.json.TownWithRacersExportDTO;
import org.softuni.mostwanted.entities.models.Town;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.jws.WebParam;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TownController {

    private TownService townService;
    private Parser parser;
    private TownRepository townRepository;
    private FileIO fileIO;


    @Autowired
    public TownController(TownService townService,
                          @Qualifier("JSONParser") Parser parser, TownRepository townRepository, FileIO fileIO) {
        this.townService = townService;
        this.parser = parser;
        this.townRepository = townRepository;
        this.fileIO = fileIO;
    }

    public String townImportJSON(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        TownJSONImportDTO[] animals = parser.read(TownJSONImportDTO[].class, jsonContent);
        Arrays.stream(animals).forEach(a -> {
            if (ValidationUtil.isValid(a)) {
                try {
                    this.townService.create(a);
                    sb.append(String.format("Successfully imported Town - %s.", a.getName())).append(System.lineSeparator());

                } catch (IllegalArgumentException error) {
                    sb.append("Error: Duplicate Data!").append(System.lineSeparator());
                }
            } else {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
            }
        });


        return sb.toString();
    }

    public String exportAllTownsWithRacersJSON() throws IOException, JAXBException {
        List<Town> allTowns = this.townRepository.findAll();
        List<Town> towns = allTowns.stream().filter(x -> x.getRacers().size() > 0).collect(Collectors.toList());
        TownWithRacersExportDTO[] dtos = new TownWithRacersExportDTO[towns.size()];
        int c=0;
        for (Town town : towns) {
            TownWithRacersExportDTO townDto = new TownWithRacersExportDTO();
            townDto.setName(town.getName());
            townDto.setRacers(town.getRacers().size());
            dtos[c] = townDto;
            c++;
        }
        List<TownWithRacersExportDTO> collect = Arrays.stream(dtos).sorted((a, b) -> {
            Integer aR = a.getRacers();
            Integer bR = b.getRacers();
            if (aR.equals(bR)) {
                return a.getRacers().compareTo(b.getRacers());
            }
            return bR - aR;
        }).collect(Collectors.toList());
        return this.parser.write(collect);



    }
}


