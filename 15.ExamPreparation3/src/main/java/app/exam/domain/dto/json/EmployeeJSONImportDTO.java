package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeJSONImportDTO {

    @Expose
    @Size(min = 3, max = 30)
    @NotNull
    private String name;
    @Expose
    @Min(15)
    @Max(80)
    @NotNull
    private Integer age;
    @Expose
    @Size(min = 3, max = 30)
    @NotNull
    private String position;

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

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
