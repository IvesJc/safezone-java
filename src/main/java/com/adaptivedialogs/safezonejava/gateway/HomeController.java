package com.adaptivedialogs.safezonejava.gateway;

import ch.qos.logback.core.model.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OAuth2User user) {
        model.addAttribute("nome", user.getAttribute("name"));
        return "home"; // você precisa criar esse home.html
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
