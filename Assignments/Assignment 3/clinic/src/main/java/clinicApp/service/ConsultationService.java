package clinicApp.service;

import clinicApp.dto.ConsultationDto;
import clinicApp.model.Consultation;
import clinicApp.model.Patient;
import clinicApp.model.User;

import java.util.List;

public interface ConsultationService {

    boolean update(ConsultationDto consultationDto);

    boolean create(ConsultationDto consultationDto);

    boolean delete(int id);

    boolean changeInfo(int id, String observations);

    List<Consultation> findAll();

    List<Consultation> findByDoctor(String doctor);

    List<Consultation> view(String doctor);
}
