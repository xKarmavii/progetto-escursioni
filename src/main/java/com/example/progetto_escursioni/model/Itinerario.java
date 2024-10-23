package com.example.progetto_escursioni.model;

import jakarta.persistence.*;

@Entity
@Table(name = "itinerari")
public class Itinerario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nome;

    @Column
    private String regione;

    @Column
    private String mappa;

    @Column
    private int distanza;

    @Column
    private int dislivello;

    @Column(name = "durata_media")
    private int durataMedia;

    @Column
    private String descrizione;

    @Column
    private String difficolta;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_guida", referencedColumnName = "id")
    private Guida guida;

    @Column
    private double prezzo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getMappa() {
        return mappa;
    }

    public void setMappa(String mappa) {
        this.mappa = mappa;
    }

    public int getDistanza() {
        return distanza;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }

    public int getDislivello() {
        return dislivello;
    }

    public void setDislivello(int dislivello) {
        this.dislivello = dislivello;
    }

    public int getDurataMedia() {
        return durataMedia;
    }

    public void setDurataMedia(int durataMedia) {
        this.durataMedia = durataMedia;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(String difficolta) {
        this.difficolta = difficolta;
    }

    public Guida getGuida() {
        return guida;
    }

    public void setGuida(Guida guida) {
        this.guida = guida;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
