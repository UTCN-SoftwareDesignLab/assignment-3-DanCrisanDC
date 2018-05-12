package clinicApp.service;

import clinicApp.dto.PatientDto;
import clinicApp.model.Patient;
import clinicApp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public boolean create(PatientDto patientDto) {

        Patient patient = new Patient(patientDto.getName(), patientDto.getCardNo(), patientDto.getCNP(), patientDto.getBirthDate(), patientDto.getAddress());
        patientRepository.save(patient);
        return patientRepository.findById(patientDto.getId()).isPresent();
    }

    @Override
    public Patient createPatient(PatientDto patientDto) {

        Patient patient = new Patient(patientDto.getName(), patientDto.getCardNo(), patientDto.getCNP(), patientDto.getBirthDate(), patientDto.getAddress());
        patientRepository.save(patient);
        return patient;
    }

    @Override
    public boolean update(PatientDto patientDto) {

        Patient patient = patientRepository.findById(patientDto.getId()).get();
        patient.setName(patientDto.getName());
        patient.setCardNo(patientDto.getCardNo());
        patient.setCNP(patientDto.getCNP());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setAddress(patientDto.getAddress());
        patientRepository.save(patient);

        return patientRepository.findById(patientDto.getId()).isPresent();
    }

    @Override
    public boolean findById(int id) {
        return patientRepository.findById(id).isPresent();
    }

    @Override
    public Patient findByName(String name) {
        return patientRepository.findByName(name);
    }

    @Override
    public void deleteAll() {
        patientRepository.deleteAll();
    }
}
