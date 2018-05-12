package clinicApp.service;

import clinicApp.dto.PatientDto;
import clinicApp.model.Patient;

public interface PatientService {
    boolean create(PatientDto patientDto);

    Patient createPatient(PatientDto patientDto);

    boolean update(PatientDto patientDto);

    boolean findById(int id);

    Patient findByName(String name);

    void deleteAll();
}
