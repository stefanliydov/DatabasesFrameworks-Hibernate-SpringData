package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.parser.JSONParser;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

public class AnimalController {

    private AnimalService animalService;
    private final Parser jsonParser;

    @Autowired
    public AnimalController(AnimalService animalService, @Qualifier("JSONParser") Parser jsonParser) {
        this.animalService = animalService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb=  new StringBuilder();
        AnimalJSONImportDTO[] animals = jsonParser.read(AnimalJSONImportDTO[].class,jsonContent);
        Arrays.stream(animals).forEach(a->{

            if(ValidationUtil.isValid(a)) {
                try {
                    this.animalService.create(a);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sb.append(String.format("Record %s successfully imported.", a.getName())).append(System.lineSeparator());
            }else {
                sb.append("Error: Invalid data.").append(System.lineSeparator());
            }

        });


        return sb.toString();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) throws IOException, JAXBException {
        return this.jsonParser.write(this.animalService.findByOwnerPhoneNumber(phoneNumber));
    }
}
