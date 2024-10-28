package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Candidato;
import com.example.progetto_escursioni.model.Prenotazione;
import com.example.progetto_escursioni.model.Utente;
import com.example.progetto_escursioni.service.CandidatoService;
import com.example.progetto_escursioni.service.PrenotazioneService;
import com.example.progetto_escursioni.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/areariservata")
public class AreaRiservataController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public String getPage(HttpSession session,
                          Model model,
                          @ModelAttribute("utente") Utente utente,
                          @RequestParam(name = "candidatura", required = false) String successoCandidatura) {

        if (session.getAttribute("utente") != null) {
            utente = (Utente) session.getAttribute("utente");
            model.addAttribute("utente", utente);
            Candidato candidato = new Candidato();
            model.addAttribute("candidato", candidato);
            List<Prenotazione> prenotazioni = prenotazioneService.elencoPrenotazioniUtente(utente.getId());
            model.addAttribute("prenotazioni", prenotazioni);

            // messaggi di successo o errore per candidatura
            if(successoCandidatura != null && successoCandidatura.equals("true")){
                model.addAttribute("messaggio", "Candidatura inviata con successo!");
            } else if (successoCandidatura != null && successoCandidatura.equals("false")){
                model.addAttribute("messaggio", "Non puoi inviare un'altra candidatura!");
            }

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
    public String candidatiManager(@ModelAttribute("candidato") Candidato candidato,
                                   HttpSession session,
                                   Model model) {
        Utente utente = (Utente) session.getAttribute("utente");

        if (candidatoService.controlloCandidato(utente.getId())) {
            candidato.setUtente(utente);
            candidatoService.salvaCandidato(candidato);
            return "redirect:/areariservata?candidatura=true";
        }
        else {
            return "redirect:/areariservata?candidatura=false";
        }

    }
}
