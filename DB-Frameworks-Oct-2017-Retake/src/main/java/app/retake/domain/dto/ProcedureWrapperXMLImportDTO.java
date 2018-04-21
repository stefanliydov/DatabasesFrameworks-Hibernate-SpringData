package app.retake.domain.dto;


import app.retake.domain.models.Procedure;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "procedures")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureWrapperXMLImportDTO {

    @XmlElement(name = "procedure")
    private List<ProcedureXMLImportDTO> procedures;

    public List<ProcedureXMLImportDTO> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(List<ProcedureXMLImportDTO> procedures) {
        this.procedures = procedures;
    }
}
