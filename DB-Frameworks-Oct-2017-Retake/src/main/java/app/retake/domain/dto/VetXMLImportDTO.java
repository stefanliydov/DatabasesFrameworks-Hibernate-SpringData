package app.retake.domain.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vet")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetXMLImportDTO {
    @XmlElement
    @Size(min =3, max=40)
    private String name;
    @XmlElement
    @Size(min=3, max=50)
    private String profession;
    @XmlElement
    @Min(22)
    @Max(65)
    private Integer age;
    @XmlElement(name = "phone-number")
    @Pattern(regexp = "^(\\+359[0-9]{9}|0[0-9]{9})$")
    private String phoneNumber;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
