package clinicApp.service;

import clinicApp.dto.PatientDto;
import clinicApp.model.Patient;

public interface PatientService {
    boolean create(PatientDto patientDto);

    boolean update(PatientDto patientDto);

    Patient findByName(String name);
}
