package app.retake.domain.dto;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "procedure")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLImportDTO {

    @XmlElement
    private String vet;
    @XmlElement
    private String animal;
    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLImportDTO> animalsAids;
    private String date;
    public String getVet() {
        return this.vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }

    public String getAnimal() {
        return this.animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public List<ProcedureAnimalAidXMLImportDTO> getAnimalsAids() {
        return this.animalsAids;
    }

    public void setAnimalsAids(List<ProcedureAnimalAidXMLImportDTO> animalsAids) {
        this.animalsAids = animalsAids;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
