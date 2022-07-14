package pl.zetosoftware;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "You have logged in successfully !!";
    }

//    // Login form
//    @RequestMapping("/web/users/login")
//    public String login() {
//        return "login.html";
//    }
//
//    // Login form with error
//    @RequestMapping("/web/users/login-error")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "login.html";
//    }

}
