package com.example.progetto_escursioni.dao;

import com.example.progetto_escursioni.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDao extends CrudRepository<Utente, Integer> {
}