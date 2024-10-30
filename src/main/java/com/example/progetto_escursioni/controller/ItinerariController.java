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
    public String filtraRisultati(
            Model model,
            @RequestParam("regione") String regione,
            @RequestParam(name = "difficolta", required = false) String difficolta,
            @RequestParam(name = "ordinaPer", required = false) String ordinaPer,
            @RequestParam(name = "fromHome", required = false) String fromHome
    ){

        if(regione.equals("tutte-regioni")){
            itinerariVisualizzati = (fromHome == null) ? itinerarioService.filtraPerDifficoltaOrdinaPer(difficolta, ordinaPer) : itinerarioService.elencoItinerari(); // se l'utente viene dalla home vuol dire che ha usato il select lì presente, che presenta solo un filtro per regione
        } else {
            itinerariVisualizzati = (fromHome == null) ? itinerarioService.filtraPerRegioneDifficoltaOrdinaPer(regione, difficolta, ordinaPer) : itinerarioService.elencoItinerariRegione(regione);
        }

        // registro nel model per thymeleaf
        model.addAttribute("itinerariVisualizzati", itinerariVisualizzati);

        return "itinerari";
    }

    // per gestire il tasto per l'area riservata
    @GetMapping("/toareariservata")
    public String toAreaRiservata(HttpSession session){
        return "redirect:/areariservata";
    }

}
