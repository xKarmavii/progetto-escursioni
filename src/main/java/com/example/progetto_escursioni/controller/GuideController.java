package com.example.progetto_escursioni.controller;

import com.example.progetto_escursioni.model.Guida;
import com.example.progetto_escursioni.service.GuidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guide")
public class GuideController {

    @Autowired
    private GuidaService guidaService;

    @GetMapping
    public String getPage(Model model) {
        List<Guida> listaGuide = guidaService.elencoGuide();
        model.addAttribute("listaGuide", listaGuide);
        return "guide";
    }
}
