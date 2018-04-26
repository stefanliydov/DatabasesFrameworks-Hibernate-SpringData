package org.softuni.mostwanted.entities.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RacerJSONImportDTO {

    @Expose
    @NotNull
    private String name;
    @Expose
    private Integer age;
    @Expose
    private BigDecimal bounty;
    @Expose
    private String homeTown;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBountry() {
        return this.bounty;
    }

    public void setBountry(BigDecimal bountry) {
        this.bounty = bountry;
    }

    public String getTownName() {
        return this.homeTown;
    }

    public void setTownName(String townName) {
        this.homeTown = townName;
    }
}
