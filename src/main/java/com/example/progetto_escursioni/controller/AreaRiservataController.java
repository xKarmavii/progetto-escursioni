package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Utente;
import com.example.progetto_escursioni.service.CandidatoService;
import com.example.progetto_escursioni.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/areariservata")
public class AreaRiservataController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CandidatoService candidatoService;

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

    @PostMapping("/candidati")
    public String candidatiManager(@RequestParam("descrizione") String descrizione,
                                   @RequestParam("telefono") String telefono,
                                   @RequestParam("foto") MultipartFile foto,
                                   HttpSession session) {

        Utente utente = (Utente) session.getAttribute("utente");
        candidatoService.salvaCandidato(foto, descrizione, telefono, utente);

        return "redirect:/areariservata";
    }
}
