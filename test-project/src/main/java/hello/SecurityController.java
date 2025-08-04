package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring.support.Layout;

@Controller
public class SecurityController {
    
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @RequestMapping({"/", "/home"})
    public String home(final Model model) {
        logger.debug("Home page requested");
        logger.info("User accessed home page");
        return "home";
    }

    @Layout("layouts/logged_in")
    @RequestMapping("/hello")
    public String hello(@AuthenticationPrincipal User user, final Model model) {
        logger.debug("Hello page requested by user: {}", user.getUsername());
        model.addAttribute("name", user.getUsername());
        logger.info("Hello page rendered for authenticated user: {}", user.getUsername());
        return "hello";
    }

    @RequestMapping("/login")
    public String login(final Model model) {
        logger.debug("Login page requested");
        logger.info("User accessed login page");
        return "login";
    }
}
