package integration;

import clinicApp.dto.ConsultationDto;
import clinicApp.dto.PatientDto;
import clinicApp.dto.UserDto;
import clinicApp.service.ConsultationService;
import clinicApp.service.PatientService;
import clinicApp.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@Configuration
@EnableJpaRepositories(basePackages = "clinicApp.repository")
@PropertySource("classpath:testing.properties")
@EntityScan(basePackages ={"clinicApp.model"})
@ComponentScan({"clinicApp.application", "clinicApp.model", "clinicApp.repository", "clinicApp.service", "clinicApp.controller", "clinicApp.dto"})
public class ConsultationServiceIntegTest {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Before
    public void setup(){
        consultationService.deleteAll();
        patientService.deleteAll();
        userService.deleteAll();

        UserDto doctor = new UserDto();
        doctor.setUsername("doctor");
        doctor.setPassword("Parola99");
        doctor.setName("Doc");
        userService.create(doctor);

        PatientDto patient = new PatientDto();
        patient.setName("Pat");
        patient.setAddress("Spital");
        patient.setCardNo(123456);
        patient.setCNP("1234567890");
        patient.setBirthDate(new Date(1997-9-7));
        patientService.create(patient);
    }

    @Test
    public void createConsultation(){
        ConsultationDto consultation = new ConsultationDto();
        consultation.setScheduledDate(new Date(1997-9-7));
        consultation.setPatient(patientService.findByName("Pat"));
        consultation.setDoctor(userService.findByName("Doc"));
        consultation.setObservations("Has fever");
        Assert.assertTrue(consultationService.create(consultation));
    }
}
