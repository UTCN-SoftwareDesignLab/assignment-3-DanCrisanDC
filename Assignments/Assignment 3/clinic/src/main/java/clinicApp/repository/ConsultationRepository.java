package clinicApp.repository;

import clinicApp.model.Consultation;
import clinicApp.model.Patient;
import clinicApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {

    @Query("SELECT c FROM Consultation c WHERE c.doctor.name LIKE %:doctor%")
    List<Consultation> findByDoctor(@Param("doctor") String doctor);

    List<Consultation> findByObservations(String observations);
}
