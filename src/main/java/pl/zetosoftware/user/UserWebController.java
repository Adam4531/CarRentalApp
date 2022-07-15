package pl.zetosoftware.user;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zetosoftware.user.dto.UserLoginDto;
import pl.zetosoftware.user.dto.UserRegisterValidDto;
import pl.zetosoftware.user.dto.UserRequestDto;
import pl.zetosoftware.user.dto.UserResponseDto;

import javax.validation.Valid;

@RestController
@RequestMapping("")
public class UserWebController {

    private final UserRegistrationService userRegistrationService;

    public UserWebController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }
//    @GetMapping("/login")
//    public String showLoginForm(ModelMap modelMap) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//            return "login.html";
//        }
//        return "redirect:/";
//    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto user, BindingResult bindingResult,
                        ModelMap modelMap) {
        if(bindingResult.hasErrors()) {
            return "/";
        }
        return "/";
    }

    @PostMapping("/register")
    public UserRegisterValidDto create(@RequestBody UserRequestDto user) {
        return userRegistrationService.register(user);
    }
}
