package clinicApp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class PatientDto {

    public int id;
    @NotNull(message = "Please type in your name")
    public String name;
    @Min(100000)
    @Max(999999)
    public int cardNo;
    @Size(min = 10, max = 10, message = "CNP should be 10 digits long")
    @Pattern(regexp = "^[0-9]+$", message = "CNP should contain only digits from 0 to 9")
    public String CNP;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please type in your birth date")
    public Date birthDate;
    @NotNull(message = "Please type in your address")
    public String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
