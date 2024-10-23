package com.example.progetto_escursioni.service;

import com.example.progetto_escursioni.dao.ItinerarioDao;
import com.example.progetto_escursioni.model.Itinerario;
import org.springframework.beans.factory.annotation.Autowired;
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
}
