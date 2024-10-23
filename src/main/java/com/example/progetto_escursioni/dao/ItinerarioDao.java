package com.example.progetto_escursioni.dao;

import com.example.progetto_escursioni.model.Itinerario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItinerarioDao extends CrudRepository<Itinerario, Integer> {
    List<Itinerario> findByRegione(String regione);
}
