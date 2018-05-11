package clinicApp.controller;

import clinicApp.dto.UserDto;
import clinicApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String adminPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "adminPage";
    }

    @PostMapping(params = "createUser")
    public String createUser(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "adminPage";
        }
        userService.create(userDto);
        model.addAttribute("message", "User created successfully.");
        return "adminPage";
    }

    @PostMapping(params = "updateUser")
    public String updateUser(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "adminPage";
        }
        userService.update(userDto);
        model.addAttribute("message", "User updated successfully.");
        return "adminPage";
    }

    @PostMapping(params = "deleteUser")
    public String deleteUser(@RequestParam("id") int id, @ModelAttribute UserDto userDto, Model model) {

        if(userService.delete(id)) {
            model.addAttribute("message", "Deleted user successfully.");
        } else {
            model.addAttribute("message", "Failed to delete user.");
        }
        return "adminPage";
    }
}
