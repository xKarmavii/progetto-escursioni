package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Itinerario;
import com.example.progetto_escursioni.service.ItinerarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ItinerarioService itinerarioService;

    @GetMapping
    public String getPage(
            Model model
    ){
        // creo lista di itinerari per il carosello a fine pagina
        List<Itinerario> caroselloItinerari = itinerarioService.elencaTreItinerari();
        // registro in model per thymeleaf
        model.addAttribute("caroselloItinerari", caroselloItinerari);

        // return pagina
        return "index";
    }
}
