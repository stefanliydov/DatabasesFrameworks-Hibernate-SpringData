package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class ProcedureController {

    private ProcedureService procedureService;
    private Parser parser;

    @Autowired
    public ProcedureController(ProcedureService procedureService, @Qualifier("XMLParser") Parser parser) {
        this.procedureService = procedureService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        ProcedureWrapperXMLImportDTO procedures = parser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);

        procedures.getProcedures().forEach(a -> {
            if (ValidationUtil.isValid(a)) {
                try {
                    this.procedureService.create(a);
                    sb.append("Record successfully imported.").append(System.lineSeparator());
                } catch (ParseException ignored) {
                    sb.append("Error: Invalid data.").append(System.lineSeparator());
                }
            } else {
                sb.append("Error: Invalid data.").append(System.lineSeparator());
            }

        });
        return sb.toString();
    }

    public String exportProcedures() throws IOException, JAXBException {

        return parser.write( procedureService.exportProcedures());
    }
}
