package com.example.progetto_escursioni.dao;

import com.example.progetto_escursioni.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDao extends CrudRepository<Utente, Integer> {

    Utente findByUsernameAndPassword(String username, String password);
    Utente findByUsernameOrEmail(String username, String email);

}
