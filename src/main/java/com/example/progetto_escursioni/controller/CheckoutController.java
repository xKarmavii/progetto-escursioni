package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Candidato;
import com.example.progetto_escursioni.model.Itinerario;
import com.example.progetto_escursioni.model.Utente;
import com.example.progetto_escursioni.service.ItinerarioService;
import com.example.progetto_escursioni.service.PrenotazioneService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private ItinerarioService itinerarioService;

    @GetMapping
    public String getPage(HttpSession session,
                          Model model,
                          @RequestParam(name = "id", required = false) Integer idItinerario) {

        if(idItinerario == null) {
            return "redirect:/";
        }

        if (session.getAttribute("utente") != null) {
            Utente utente = (Utente) session.getAttribute("utente");
            model.addAttribute("utente", utente);

            Itinerario itinerario = itinerarioService.dettaglioItinerario(idItinerario);
            model.addAttribute("itinerario", itinerario);

            return "checkout";
        }
        else {
            return "redirect:/loginregistrazione";
        }
    }
}
