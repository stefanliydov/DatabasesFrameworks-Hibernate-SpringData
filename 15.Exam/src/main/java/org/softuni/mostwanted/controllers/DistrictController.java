package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.json.DistrictJSONImportDTO;
import org.softuni.mostwanted.entities.models.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class DistrictController {


    private DistrictService districtService;
    private Parser parser;
    private TownRepository townRepository;
    @Autowired
    public DistrictController(DistrictService districtService,
                              @Qualifier("JSONParser") Parser parser, TownRepository townRepository) {
        this.districtService = districtService;
        this.parser = parser;
        this.townRepository = townRepository;
    }

    public String districtJSONImport(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb=  new StringBuilder();
        DistrictJSONImportDTO[] districts = parser.read(DistrictJSONImportDTO[].class,jsonContent);
        Arrays.stream(districts).forEach(a->{

            if(ValidationUtil.isValid(a)) {
                try {
                    if (!a.getTownName().isEmpty()){
                        Town town = townRepository.findOneByName(a.getTownName());
                        if(town==null){
                            sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                        }else{
                            this.districtService.create(a);
                            sb.append(String.format("Successfully imported District - %s.",
                                    a.getName())).append(System.lineSeparator());
                        }
                    }else {
                        this.districtService.create(a);
                        sb.append(String.format("Successfully imported District - %s.",
                                a.getName())).append(System.lineSeparator());
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
