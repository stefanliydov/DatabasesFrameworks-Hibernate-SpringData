package org.softuni.mostwanted.entities.dto.xml;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesXMLImportDTO {

    @XmlAttribute(name = "has-finished")
    private boolean hasFinished;
    @XmlAttribute(name = "finish-time")
    private BigDecimal finishTime;
    @XmlAttribute(name = "car-id")
    private Integer carId;
    @XmlElement(name = "racer")
    private String racer;

    public boolean isHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public BigDecimal getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(BigDecimal finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getCarId() {
        return this.carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getRacer() {
        return this.racer;
    }

    public void setRacer(String racer) {
        this.racer = racer;
    }
}
