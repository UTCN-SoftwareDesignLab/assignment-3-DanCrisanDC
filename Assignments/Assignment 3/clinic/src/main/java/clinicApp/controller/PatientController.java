package clinicApp.controller;

import clinicApp.dto.ConsultationDto;
import clinicApp.dto.PatientDto;
import clinicApp.service.ConsultationService;
import clinicApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/patientPage")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {

        this.patientService = patientService;
    }

    @GetMapping()
    public String patientPage(Model model) {

        model.addAttribute("patientDto", new PatientDto());
        return "patientPage";
    }

    @PostMapping(params = "addPatient")
    public String addPatient(@ModelAttribute @Valid PatientDto patientDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "patientPage";
        }

        if(patientService.create(patientDto)) {
            model.addAttribute("message", "Patient added successfully");
        } else {
            model.addAttribute("message", "Failed to add patient");
        }
        return "patientPage";
    }


    @PostMapping(params = "updatePatient")
    public String updatePatient(@ModelAttribute @Valid PatientDto patientDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "patientPage";
        }

        if(patientService.update(patientDto)) {
            model.addAttribute("message", "Patient updated successfully");
        } else {
            model.addAttribute("message", "Failed to update patient");
        }
        return "patientPage";
    }
}
