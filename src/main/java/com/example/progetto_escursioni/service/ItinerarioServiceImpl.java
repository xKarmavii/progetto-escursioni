package com.example.progetto_escursioni.service;

import com.example.progetto_escursioni.dao.ItinerarioDao;
import com.example.progetto_escursioni.model.Itinerario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItinerarioServiceImpl implements ItinerarioService {

    @Autowired
    private ItinerarioDao itinerarioDao;

    @Override
    public List<Itinerario> elencoItinerari() {
        return (List<Itinerario>) itinerarioDao.findAll();
    }

    @Override
    public Itinerario dettaglioItinerario(int idItinerario) {
        Optional<Itinerario> itinerarioOptional = itinerarioDao.findById(idItinerario);
        if (itinerarioOptional.isPresent()) {
            return itinerarioOptional.get();
        }
        return null;
    }

    @Override
    public List<Itinerario> elencoItinerariRegione(String regione) {
        return itinerarioDao.findByRegione(regione);
    }

    // metodi per filtri

    @Override
    public List<Itinerario> filtraPerRegioneDifficoltaOrdinaPer(String regioneSelezionata, String difficolta, String ordinaPerSelezionato) {

        // filtro per la difficoltà selezionata
        String difficoltaUno = ""; // facile
        String difficoltaDue = ""; // media
        String difficoltaTre = ""; // difficile

        if(difficolta != null) { // se difficolta è null vuol dire che l'utente non ha selezionato nessun filtro di difficoltà e allora va bene che difficoltaUno, -Due e -Tre restino vuote

            // creo array a partire dalla stringa "difficolta" che presenta struttura: "a,b,c"
            String[] difficoltaArray = difficolta.split(","); // nel caso in cui c'è solo 1 difficoltà selezionata, si ottiene un array che contiene 1 singolo elemento

            if (difficoltaArray.length == 3) { // "a,b,c"
                difficoltaUno = difficoltaArray[0];
                difficoltaDue = difficoltaArray[1];
                difficoltaTre = difficoltaArray[2];
            } else if (difficoltaArray.length == 2) { // "a,b"
                difficoltaUno = difficoltaArray[0];
                difficoltaDue = difficoltaArray[1];
            } else if (difficoltaArray.length == 1){ // "a"
                difficoltaUno = difficoltaArray[0];
            }

        }

        // con una nativequery abbiamo difficoltà con l'utilizzo di order by, ma possiamo invece usare Sort
        Sort sort = Sort.by(Sort.Direction.ASC, ordinaPerSelezionato); // così ordina in senso ascendente in base al valore di ordinaPerSelezionato (che prende un value dal form che corrisponde al nome di una colonna della tabella sul db)

        return itinerarioDao.selectItinerariWhereRegioneAndDifficoltaOrderBy(regioneSelezionata, difficoltaUno, difficoltaDue, difficoltaTre, sort);
    }

    @Override
    public List<Itinerario> filtraPerDifficoltaOrdinaPer(String difficolta, String ordinaPerSelezionato) { // vedi commenti metodo precedente

        // difficoltà selezionata
        String difficoltaUno = "";
        String difficoltaDue = "";
        String difficoltaTre = "";

        if(difficolta != null) {

            String[] difficoltaArray = difficolta.split(",");

            if (difficoltaArray.length == 3) {
                difficoltaUno = difficoltaArray[0];
                difficoltaDue = difficoltaArray[1];
                difficoltaTre = difficoltaArray[2];
            } else if (difficoltaArray.length == 2) {
                difficoltaUno = difficoltaArray[0];
                difficoltaDue = difficoltaArray[1];
            } else if (difficoltaArray.length == 1){
                difficoltaUno = difficoltaArray[0];
            }

        }

        // sort
        Sort sort = Sort.by(Sort.Direction.ASC, ordinaPerSelezionato);;

        return itinerarioDao.selectItinerariWhereDifficoltaOrderBy(difficoltaUno, difficoltaDue, difficoltaTre, sort);
    }

    @Override
    public List<Itinerario> cercaItinerarioPerNomeLike(String ricercaItinerario) {
        return itinerarioDao.findByNomeContaining(ricercaItinerario);
    }


}
