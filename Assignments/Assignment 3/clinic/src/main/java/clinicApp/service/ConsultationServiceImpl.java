package clinicApp.service;

import clinicApp.dto.ConsultationDto;
import clinicApp.model.Consultation;
import clinicApp.model.Patient;
import clinicApp.model.User;
import clinicApp.repository.ConsultationRepository;
import clinicApp.repository.PatientRepository;
import clinicApp.repository.UserRepository;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private ConsultationRepository consultationRepository;
    private UserRepository userRepository;
    private PatientRepository patientRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository, UserRepository userRepository, PatientRepository patientRepository) {
        this.consultationRepository = consultationRepository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }


    @Override
    public boolean update(ConsultationDto consultationDto) {

        Patient patient;
        User doctor;

        if(patientRepository.findByName(consultationDto.getPatient().getName()) != null ) {
            patient = patientRepository.findByName(consultationDto.getPatient().getName());
        } else {
            return false;
        }
        if(userRepository.findByName(consultationDto.getDoctor().getName()) != null) {
            doctor = userRepository.findByName(consultationDto.getDoctor().getName());
        } else {
            return false;
        }

        Consultation consultation = consultationRepository.findById(consultationDto.getId()).get();
        List<Consultation> consultations = consultationRepository.findByDoctor(doctor.getName());
        for(Consultation c:consultations){

            if(consultationDto.getPatient().getName() != c.getPatient().getName() && Math.abs(c.getScheduledDate().getTime() - consultationDto.getScheduledDate().getTime())< 86400 * 1000 + 1) {
                return false;
            }
        }
        consultation.setScheduledDate(consultationDto.getScheduledDate());
        consultation.setPatient(patient);
        consultation.setDoctor(doctor);
        consultation.setObservations(consultationDto.getObservations());
        consultationRepository.save(consultation);
        return true;
    }

    @Override
    public boolean create(ConsultationDto consultationDto) {
        Patient patient;
        User doctor;

        if(patientRepository.findByName(consultationDto.getPatient().getName()) != null ) {
            patient = patientRepository.findByName(consultationDto.getPatient().getName());
        } else {
            return false;
        }
        if(userRepository.findByName(consultationDto.getDoctor().getName()) != null) {
            doctor = userRepository.findByName(consultationDto.getDoctor().getName());
        } else {
            return false;
        }
        List<Consultation> consultations = consultationRepository.findByDoctor(doctor.getName());
        for(Consultation c:consultations){

            if(Math.abs(c.getScheduledDate().getTime() - consultationDto.getScheduledDate().getTime())<86400 * 1000 + 1) {
                return false;
            }
        }
        Consultation consultation = new Consultation(consultationDto.getScheduledDate(), patient, doctor, consultationDto.getObservations());
        consultationRepository.save(consultation);
        return true;
    }

    @Override
    public boolean delete(int id) {
        consultationRepository.deleteById(id);
        return consultationRepository.findById(id).isPresent();
    }

    @Override
    public boolean changeInfo(int id, String observations) {

        Consultation consultation = consultationRepository.findById(id).get();
        consultation.setObservations(observations);
        consultationRepository.save(consultation);
        if (consultationRepository.findById(id).get().getObservations().equals(observations)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }

    @Override
    public List<Consultation> findByDoctor(String doctor) {
        return consultationRepository.findByDoctor(doctor);
    }

    @Override
    public List<Consultation> view(String doctor) {
        List<Consultation> consultation = consultationRepository.findByDoctor(doctor);
        return consultation;
    }

//    private Date getDate(String dateString) {
//        Date date = null;
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
//            System.out.println(date + "!!!");
//            return date;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
