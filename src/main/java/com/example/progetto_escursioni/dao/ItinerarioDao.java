package com.example.progetto_escursioni.dao;

import com.example.progetto_escursioni.model.Itinerario;
import org.springframework.data.repository.CrudRepository;

public interface ItinerarioDao extends CrudRepository<Itinerario, Integer> {
}
