package com.example.progetto_escursioni.service;

import com.example.progetto_escursioni.model.Itinerario;

import java.util.List;

public interface ItinerarioService {

    List<Itinerario> elencoItinerari();
    Itinerario dettaglioItinerario(int idItinerario);
    List<Itinerario> elencoItinerariRegione(String regione);
}
