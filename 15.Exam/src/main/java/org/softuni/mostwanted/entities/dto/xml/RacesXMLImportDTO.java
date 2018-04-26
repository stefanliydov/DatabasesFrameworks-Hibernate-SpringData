package org.softuni.mostwanted.entities.dto.xml;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacesXMLImportDTO {

    @XmlElement(name = "laps")
    private Integer laps;
    @XmlElement(name = "district-name")
    private String districtName;
    @XmlElementWrapper(name ="entries")
    private List<Integer> entriesId;

    public RacesXMLImportDTO() {
        this.entriesId = new ArrayList<>();
    }

    public Integer getLaps() {
        return this.laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<Integer> getEntriesId() {
        return this.entriesId;
    }

    public void setEntriesId(List<Integer> entriesId) {
        this.entriesId = entriesId;
    }
}
