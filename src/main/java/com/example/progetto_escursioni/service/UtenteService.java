package com.example.progetto_escursioni.service;

import com.example.progetto_escursioni.model.Utente;

public interface UtenteService {

    void salvaUtente(Utente utente);
    Utente dettaglioUtente(int idUtente);
}
