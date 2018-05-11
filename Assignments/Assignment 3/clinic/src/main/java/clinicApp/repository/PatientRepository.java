package clinicApp.repository;

import clinicApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Patient findByName(String name);
}
