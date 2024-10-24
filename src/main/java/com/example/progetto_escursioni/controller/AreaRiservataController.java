package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Utente;
import com.example.progetto_escursioni.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/areariservata")
public class AreaRiservataController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(HttpSession session,
                          Model model) {

        if (session.getAttribute("utente") != null) {
            Utente utente = (Utente) session.getAttribute("utente");
            model.addAttribute("utente", utente);
            return "areariservata";
        }
        else {
            return "redirect:/loginregistrazione";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("utente");
        return "redirect:/";
    }
}
