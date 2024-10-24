package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Itinerario;
import com.example.progetto_escursioni.service.ItinerarioService;
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
    public String getPage(Model model){
        itinerariVisualizzati = itinerarioService.elencoItinerari();
        model.addAttribute("itinerariVisualizzati", itinerariVisualizzati);
        return "itinerari";
    }

    @PostMapping
    public String formManager(
            Model model,
            @RequestParam("regione") String regione
    ){
        if(regione.equals("tutteregioni")){
            itinerariVisualizzati = itinerarioService.elencoItinerari();
        } else {
            itinerariVisualizzati = itinerarioService.elencoItinerariRegione(regione);
        }
        model.addAttribute("itinerariVisualizzati", itinerariVisualizzati);
        return "itinerari";
    }

}
