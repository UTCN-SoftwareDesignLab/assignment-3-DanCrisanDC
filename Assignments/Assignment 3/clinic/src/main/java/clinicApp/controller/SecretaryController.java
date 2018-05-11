package clinicApp.controller;

import clinicApp.dto.ConsultationDto;
import clinicApp.dto.PatientDto;
import clinicApp.dto.UserDto;
import clinicApp.model.Role;
import clinicApp.model.User;
import clinicApp.service.ConsultationService;
import clinicApp.service.PatientService;
import clinicApp.service.UserService;
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
@RequestMapping(value = "/secretaryPage")
public class SecretaryController {

    private ConsultationService consultationService;
    private PatientService patientService;

    @Autowired
    public SecretaryController(ConsultationService consultationService, PatientService patientService) {

        this.consultationService = consultationService;
        this.patientService = patientService;
    }

    @GetMapping()
    public String secretaryPage(Model model) {

        model.addAttribute("consultationDto", new ConsultationDto());
        model.addAttribute("patientDto", new PatientDto());
        return "secretaryPage";
    }

    @PostMapping(params = "consultationsPage")
    public String consultationsPage(@ModelAttribute ConsultationDto consultationDto) {

        return "redirect:/consultationsPage";
    }


    @PostMapping(params = "patientPage")
    public String patientPage(@ModelAttribute PatientDto patientDto) {

        return "patientPage";
    }
}
