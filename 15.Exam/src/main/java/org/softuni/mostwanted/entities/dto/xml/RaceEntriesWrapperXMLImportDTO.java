package org.softuni.mostwanted.entities.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesWrapperXMLImportDTO {

    @XmlElement(name ="race-entry")
    private List<RaceEntriesXMLImportDTO> raceEntries;

    public List<RaceEntriesXMLImportDTO> getRaceEntries() {
        return this.raceEntries;
    }

    public void setRaceEntries(List<RaceEntriesXMLImportDTO> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
