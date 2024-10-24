package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/loginregistrazione")
public class LoginRegistrazioneController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage() {
        return "login-registrazione";
    }

    @PostMapping("/login")
    public String loginManager(HttpSession session,
                               Model model,
                               @RequestParam("login-username") String username,
                               @RequestParam("login-password") String password) {

        if (utenteService.loginUtente(session, username, password)) {
            return "redirect:/areariservata";
        }
        else {
            model.addAttribute("erroreLogin", "Credenziali errate");
        }
        return "login-registrazione";
    }

    @PostMapping("/registrazione")
    public String registrazioneManager() {
        return null;
    }
}
