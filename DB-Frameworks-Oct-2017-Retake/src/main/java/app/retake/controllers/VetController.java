package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class VetController {

    private final VetService vetService;
    private final Parser parser;

    @Autowired
    public VetController(VetService vetService,
                         @Qualifier("XMLParser") Parser parser) {
        this.vetService = vetService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        VetWrapperXMLImportDTO vets = parser.read(VetWrapperXMLImportDTO.class, xmlContent);

        vets.getVets().forEach(a->{
            if(ValidationUtil.isValid(a)) {
                this.vetService.create(a);
                sb.append(String.format("Vet %s successfully imported.", a.getName())).append(System.lineSeparator());
            }else {
                sb.append("Error: Invalid data.").append(System.lineSeparator());
            }

        });


        return sb.toString();
    }
}
