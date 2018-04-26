package org.softuni.mostwanted.entities.dto.json;

import com.google.gson.annotations.Expose;

import java.util.List;

public class RacingCarsJSONExport {

    @Expose
    private String name;
    @Expose
    private Integer age;
    @Expose
    private List<String> cars;

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

    public List<String> getCars() {
        return this.cars;
    }

    public void setCars(List<String> cars) {
        this.cars = cars;
    }
}
