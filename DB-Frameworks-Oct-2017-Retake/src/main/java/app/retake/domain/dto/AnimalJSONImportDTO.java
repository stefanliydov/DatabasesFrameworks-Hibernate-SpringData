package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AnimalJSONImportDTO implements Serializable {

    @Expose
    @Size(min=3, max = 20, message = "Name size incorrect!")
    @NotNull
    private String name;
    @Expose
    @Size(min=3, max = 20, message = "Name size incorrect!")
    @NotNull
    private String type;
    @Expose
    @Min(1)
    private Integer age;
    @Expose
    private PassportJSONImportDTO passport;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PassportJSONImportDTO getPassport() {
        return this.passport;
    }

    public void setPassport(PassportJSONImportDTO passport) {
        this.passport = passport;
    }
}
