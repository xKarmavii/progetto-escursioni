package com.example.progetto_escursioni.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "data_prenotazione")
    private LocalDateTime dataPrenotazione;
    @Column(name = "data_escursione")
    private LocalDate dataEscursione;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_itinerario", referencedColumnName = "id")
    private Itinerario itinerario;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_utente", referencedColumnName = "id")
    private Utente utente;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDateTime dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public LocalDate getDataEscursione() {
        return dataEscursione;
    }

    public void setDataEscursione(LocalDate dataEscursione) {
        this.dataEscursione = dataEscursione;
    }

    public Itinerario getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
