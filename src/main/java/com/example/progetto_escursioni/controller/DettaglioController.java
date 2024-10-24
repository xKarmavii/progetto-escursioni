package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Foto;
import com.example.progetto_escursioni.model.Itinerario;
import com.example.progetto_escursioni.service.ItinerarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dettaglio")
public class DettaglioController {

    @Autowired
    private ItinerarioService itinerarioService;

    @GetMapping
    public String getPage(
            Model model,
            @RequestParam("id") int idItinerario
    ){


        // creo un oggetto itinerario in base all'id fornito e lo registro
        Itinerario itinerario = itinerarioService.dettaglioItinerario(idItinerario);
        model.addAttribute("itinerario", itinerario);
        // recupero la lista delle foto associate all'itinerario e le registro
        List<Foto> fotoItinerario = itinerario.getFotoItinerario();
        model.addAttribute("fotoItinerario", fotoItinerario);

        return "dettaglio";

// DA AGGIUSTARE
    }
}
