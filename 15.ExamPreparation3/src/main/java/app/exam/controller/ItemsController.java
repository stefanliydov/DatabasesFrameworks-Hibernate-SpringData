package app.exam.controller;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.parser.ValidationUtil;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

@Controller
public class ItemsController {

    private ItemsService itemsService;
    private Parser parser;

    @Autowired
    public ItemsController(ItemsService itemsService, @Qualifier("JSONParser") Parser parser) {
        this.itemsService = itemsService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        ItemJSONImportDTO[] items = parser.read(ItemJSONImportDTO[].class, jsonContent);

        Arrays.stream(items).forEach(a->{
            if (ValidationUtil.isValid(a)) {
                try {
                    this.itemsService.create(a);
                    sb.append(String.format("Record %s successfully imported.",a.getName())).append(System.lineSeparator());
                } catch (IllegalArgumentException ignored) {
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            } else {
                sb.append("Error: Invalid data.").append(System.lineSeparator());
        }});
        return sb.toString();
    }
}
