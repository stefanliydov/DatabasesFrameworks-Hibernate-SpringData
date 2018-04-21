package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.parser.JSONParser;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class AnimalAidController {

    private AnimalAidService animalAidService;
    private final Parser jsonParser;

    @Autowired
    public AnimalAidController(AnimalAidService animalAidService, @Qualifier("JSONParser") Parser jsonParser) {
        this.animalAidService = animalAidService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        AnimalAidJSONImportDTO[] animalAids = jsonParser.read(AnimalAidJSONImportDTO[].class, jsonContent);

        Arrays.stream(animalAids).forEach(a->{

                if(ValidationUtil.isValid(a)) {
                    this.animalAidService.create(a);
                    sb.append(String.format("Record %s successfully imported.", a.getName())).append(System.lineSeparator());
                }else {
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }

        });


        return sb.toString();
    }
}
