package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class PassportJSONImportDTO implements Serializable {

    @Expose
    @Pattern(regexp = "^\\w{7}[0-9]{3}$",message = "Invalid serial number")
    private String serialNumber;
    @Expose
    @Pattern(regexp = "^(\\+359[0-9]{9}|0[0-9]{9})$")
    @NotNull
    private String ownerPhoneNumber;
    @Expose
    @Size(min=3,max=30, message = "Owner's name is invalid!")
    private String ownerName;
    @Expose
    private Date registrationDate;

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOwnerPhoneNumber() {
        return this.ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
