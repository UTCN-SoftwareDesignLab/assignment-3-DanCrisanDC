package clinicApp.controller;

import clinicApp.dto.ConsultationDto;
import clinicApp.model.Consultation;
import clinicApp.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/doctorPage")
public class DoctorController {
    private ConsultationService consultationService;

    @Autowired
    public DoctorController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping()
    public String doctorPage(Model model) {
        model.addAttribute("consultationDto", new ConsultationDto());
        return "doctorPage";
    }

    @PostMapping(params = "addDetails")
    public String addDetails(@RequestParam("id") int id, @RequestParam("observations") String observations, Model model) {

        if(consultationService.changeInfo(id, observations)) {
            model.addAttribute("message", "Consultation info changed successfully");
        } else {
            model.addAttribute("message", "Failed to change info");
        }
        return "doctorPage";
    }

    @PostMapping(params = "viewConsultation")
    public String viewConsultation(@RequestParam("doctor") String doctor, Model model) {

        List<Consultation> consultations = consultationService.view(doctor);
        model.addAttribute("consultation", consultations);
        return "resultsPage";
    }
}
