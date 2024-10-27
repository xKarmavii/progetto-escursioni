package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Candidato;
import com.example.progetto_escursioni.model.Itinerario;
import com.example.progetto_escursioni.model.Prenotazione;
import com.example.progetto_escursioni.model.Utente;
import com.example.progetto_escursioni.service.ItinerarioService;
import com.example.progetto_escursioni.service.PrenotazioneService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    // gestisce la richiesta dal javascript per la modifica del prezzo totale dell'ordine in base al numero dei partecipanti
    @PostMapping("/modificaPrezzo")
    @ResponseBody
    public String modificaPrezzo(
            @RequestParam("id") int idItinerario,
            @RequestParam("partecipanti") int partecipanti){
        Itinerario itinerario = itinerarioService.dettaglioItinerario(idItinerario);

        DecimalFormat format = new DecimalFormat("#.##");
        format.setRoundingMode((RoundingMode.HALF_EVEN));

        return "€" + format.format((itinerario.getPrezzo()*partecipanti));
    }

    // gestisce l'invio della prenotazione (submit)
    @PostMapping("/invia")
    public String invioPrenotazione(HttpSession session,
                                    @RequestParam("numeroPartecipanti") int numeroPartecipanti,
                                    @RequestParam("totale") String totaleString,
                                    @RequestParam("idItinerario") int idItinerario){
        Prenotazione prenotazione = new Prenotazione();
        Utente utente = (Utente) session.getAttribute("utente");
        Itinerario itinerario = itinerarioService.dettaglioItinerario(idItinerario);

        LocalDateTime dataPrenotazione = LocalDateTime.now();
        LocalDate dataEscursione = LocalDate.now(); // DA CAMBIARE! CI SIAMO SCORDATI DI IMPLEMENTARE LE DATE PER GLI ITINERARI. AL MOMENTO COME PLACEHOLDER PRENDE LA DATA ODIERNA
        double prezzoTotale = Double.parseDouble(totaleString.replace("€","").replace(",","."));

        prenotazione.setDataPrenotazione(dataPrenotazione);
        prenotazione.setDataEscursione(dataEscursione);
        prenotazione.setNumeroPartecipanti(numeroPartecipanti);
        prenotazione.setPrezzoTotale(prezzoTotale);
        prenotazione.setItinerario(itinerario);
        prenotazione.setUtente(utente);

        prenotazioneService.salvaPrenotazione(prenotazione);

        return "redirect:/itinerari";
    }
}
