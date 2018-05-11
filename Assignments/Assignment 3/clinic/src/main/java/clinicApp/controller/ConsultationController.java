package clinicApp.controller;

import clinicApp.dto.ConsultationDto;
import clinicApp.dto.NotificationDto;
import clinicApp.dto.PatientDto;
import clinicApp.dto.UserDto;
import clinicApp.model.User;
import clinicApp.service.ConsultationService;
import clinicApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/consultationsPage")
public class ConsultationController {

    private SimpMessagingTemplate simpMessagingTemplate;

    private ConsultationService consultationService;
    private UserService userService;

    @Autowired
    public ConsultationController(ConsultationService consultationService, UserService userService, SimpMessagingTemplate simpMessagingTemplate) {

        this.consultationService = consultationService;
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping()
    public String consultationsPage(Model model) {

        model.addAttribute("consultationDto", new ConsultationDto());
        return "consultationsPage";
    }

    @PostMapping(params = "createConsultation")
    @MessageMapping("/send")
    public String createConsultation(@ModelAttribute @Valid ConsultationDto consultationDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "consultationsPage";
        }

        if(consultationService.create(consultationDto)) {
            model.addAttribute("message", "Consultation added successfully");
        } else {
            model.addAttribute("message", "Please schedule at another date");
        }

        NotificationDto notificationDto = new NotificationDto();
//        notificationDto.setContent("Patient " + consultationDto.getPatient().getName() + " has arrived for the " + consultationDto.getScheduledDate().toString() + " consultation.");
        notificationDto.setContent("Patient consultation.");

        User doctor = userService.findByName(consultationDto.getDoctor().getName());

        System.out.println(consultationDto.getDoctor().getName() + " " + doctor.getName() + " " + doctor.getUsername());

        simpMessagingTemplate.convertAndSendToUser(doctor.getUsername(), "/queue/reply", notificationDto);
        return "consultationsPage";
    }


    @PostMapping(params = "updateConsultation")
    public String updateConsultation(@ModelAttribute @Valid ConsultationDto consultationDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "consultationsPage";
        }

        if(consultationService.update(consultationDto)) {
            model.addAttribute("message", "Consultation updated successfully");
        } else {
            model.addAttribute("message", "Failed to update");
        }
        return "consultationsPage";
    }

    @PostMapping(params = "deleteConsultation")
    public String deleteConsultation(@RequestParam("id") int id, Model model) {

        if(!consultationService.delete(id)) {
            model.addAttribute("message", "Consultation deleted successfully");
        } else {
            model.addAttribute("message","Failed to delete consultation");
        }
        return "consultationsPage";
    }
}
