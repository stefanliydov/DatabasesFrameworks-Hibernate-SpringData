package org.softuni.mostwanted.entities.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class DistrictJSONImportDTO {

    @Expose
    @NotNull
    private String name;
    @Expose
    private String townName;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return this.townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
