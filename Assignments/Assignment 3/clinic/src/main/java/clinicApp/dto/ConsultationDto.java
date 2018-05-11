package clinicApp.dto;

import clinicApp.model.Patient;
import clinicApp.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ConsultationDto {

    public int id;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date scheduledDate;
    @NotNull(message = "Please add a patient")
    public Patient patient;
    @NotNull(message = "Please add a doctor")
    public User doctor;
    public String observations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
