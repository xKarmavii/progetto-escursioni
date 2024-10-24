package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Utente;
import com.example.progetto_escursioni.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loginregistrazione")
public class LoginRegistrazioneController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(Model model) {
        Utente utente = new Utente();
        model.addAttribute("utente", utente);
        return "login-registrazione";
    }

    @PostMapping("/login")
    public String loginManager(HttpSession session,
                               Model model,
                               @ModelAttribute("utente") Utente utente,
                               @RequestParam("login-username") String username,
                               @RequestParam("login-password") String password) {

        if (utenteService.loginUtente(session, username, password)) {
            return "redirect:/areariservata";
        }
        else {
            model.addAttribute("messaggio", "Credenziali errate");
        }
        return "login-registrazione";
    }

    @PostMapping("/registrazione")
    public String registrazioneManager(Model model,
                                       @ModelAttribute("utente") Utente utente) {

        utenteService.salvaUtente(utente);
        model.addAttribute("messaggio", "Registrazione effettuata");
        return "login-registrazione";
    }
}
