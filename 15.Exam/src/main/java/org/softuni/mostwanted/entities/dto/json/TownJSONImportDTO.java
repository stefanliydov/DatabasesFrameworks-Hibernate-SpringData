package org.softuni.mostwanted.entities.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownJSONImportDTO {

    @Expose
    @NotNull
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
