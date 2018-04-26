package org.softuni.mostwanted.entities.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacesWrapperXMLImportDTO {

    @XmlElement(name = "race")
    private List<RacesXMLImportDTO> races;

    public List<RacesXMLImportDTO> getRaces() {
        return this.races;
    }

    public void setRaces(List<RacesXMLImportDTO> races) {
        this.races = races;
    }
}
