package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Itinerario;
import com.example.progetto_escursioni.service.ItinerarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/itinerari")
public class ItinerariController {

    List<Itinerario> itinerariVisualizzati;

    @Autowired
    private ItinerarioService itinerarioService;

    @GetMapping
    public String getPage(
            HttpSession session,
            Model model){
        // di base accedendo alla pagina si recupera la lista di tutti gli itinerari
        itinerariVisualizzati = itinerarioService.elencoItinerari();
        model.addAttribute("itinerariVisualizzati", itinerariVisualizzati);

        // registro in sessione la pagina corrente, per eventuali tasti "indietro" o per quando fai il login
        session.setAttribute("paginaPrecedente", "itinerari");

        return "itinerari";
    }

    @PostMapping
    public String formManager(
            Model model,
            @RequestParam("regione") String regione
    ){
        // recupera una lista di tutti gli itinerari o degli itinerari presenti in una specifica regione, a seconda del value della option del select (recuperato nella String regione)
        if(regione.equals("tutteregioni")){
            itinerariVisualizzati = itinerarioService.elencoItinerari();
        } else {
            itinerariVisualizzati = itinerarioService.elencoItinerariRegione(regione);
        }
        model.addAttribute("itinerariVisualizzati", itinerariVisualizzati);

        return "itinerari";
    }

    // per gestire il tasto per l'area riservata
    @GetMapping("/toareariservata")
    public String toAreaRiservata(HttpSession session){
        return "redirect:/areariservata";
    }

}
