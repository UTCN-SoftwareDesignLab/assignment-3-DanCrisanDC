package clinicApp.controller;

import clinicApp.dto.UserDto;
import clinicApp.model.Role;
import clinicApp.model.User;
import clinicApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String loginPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

    @PostMapping(params = "login")
    public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute @Valid UserDto userDto, BindingResult result) throws ServletException {

        try {
            RequestCache requestCache = new HttpSessionRequestCache();
            request.login(userDto.getUsername(),userDto.getPassword());
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if (savedRequest != null) {
                return "redirect:" + savedRequest.getRedirectUrl();
            } else {
                return "redirect:/";
            }
        } catch (ServletException authenticationFailed) {
            result.rejectValue(null, "authentication.failed");
            return "login";
        }
    }
}
