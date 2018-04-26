package org.softuni.mostwanted.entities.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CarJSONImportDTO {

    @Expose
    @NotNull
    private String brand;
    @Expose
    @NotNull
    private String model;
    @Expose
    private BigDecimal price;
    @Expose
    @NotNull
    private Integer yearOfProduction;
    @Expose
    private BigDecimal maxSpeed;
    @Expose
    private BigDecimal zeroToSixty;
    @Expose
    private String racerName;

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfProduction() {
        return this.yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public BigDecimal getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(BigDecimal maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getZeroToSixty() {
        return this.zeroToSixty;
    }

    public void setZeroToSixty(BigDecimal zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public String getRacerName() {
        return this.racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}
