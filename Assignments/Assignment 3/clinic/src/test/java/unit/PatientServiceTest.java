package unit;

import clinicApp.dto.PatientDto;
import clinicApp.model.Patient;
import clinicApp.repository.PatientRepository;
import clinicApp.service.PatientService;
import clinicApp.service.PatientServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class PatientServiceTest {

    private PatientService patientService;
    @Mock
    private PatientRepository patientRepository;

    @Before
    public void setup() {
        this.patientService = new PatientServiceImpl(patientRepository);

        Patient patient1 = new Patient("Carina", 123456, "1234567890", new Date(1996-02-26), "Teius");

        List<Patient> patients1 = new ArrayList<>();
        patients1.add(patient1);

        Mockito.when(patientRepository.findByName("Carina")).thenReturn(patient1);
        Mockito.when(patientRepository.save(patient1)).thenReturn(patient1);
    }

    @Test
    public void findByNameOrAuthorOrGenre(){
        Patient patient = patientService.findByName("Carina");
        Assert.assertEquals(patient.getCardNo(), 123456);
    }

    @Test
    public void create() {
        PatientDto patient = new PatientDto();
        Assert.assertNotNull(patientService.createPatient(patient));
    }

    @Test
    public void deleteAll() {
        PatientDto patientDto = new PatientDto();
        Patient patient = patientService.createPatient(patientDto);
        int id = patient.getId();
        patientService.deleteAll();

        Assert.assertFalse(patientService.findById(id));
    }
}
