package pl.zetosoftware.user;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zetosoftware.user.dto.UserLoginDto;
import pl.zetosoftware.user.dto.UserRequestDto;

import javax.validation.Valid;

@Controller
@RequestMapping("/web/users")
public class UserWebController {

    private final UserRegistrationService userRegistrationService;

    public UserWebController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }


    @GetMapping("/login")
    public String showLoginForm(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login.html";
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("userEntity") UserLoginDto user, BindingResult bindingResult,
                        ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            return "/";
        }
        return "/";
    }

    @GetMapping("/register")
    public String showRegistrationForm(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            modelMap.addAttribute("UserRequestDto", UserRequestDto.builder().build());
            return "register.html";
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String create(@Valid @ModelAttribute("UserRequestDto") UserRequestDto user, BindingResult bindingResult,
                         ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)  {
            if (bindingResult.hasErrors()) {
                return "register.html";
            }
            userRegistrationService.register(user);
            return "register-success.html";
        }
        return "redirect:/";
    }

}
