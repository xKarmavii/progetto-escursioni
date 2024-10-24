package com.example.progetto_escursioni.service;

import com.example.progetto_escursioni.dao.CandidatoDao;
import com.example.progetto_escursioni.model.Candidato;
import com.example.progetto_escursioni.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    @Autowired
    private CandidatoDao candidatoDao;


    @Override
    public void salvaCandidato(MultipartFile foto, String descrizione, String telefono, Utente utente) {

        Candidato candidato = new Candidato();

        try {
            String formato = foto.getContentType();

            String fotoCodificata = "data:" + formato + ";base64," + Base64.getEncoder().encodeToString(foto.getBytes());

            candidato.setFoto(fotoCodificata);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        candidato.setDescrizione(descrizione);

        candidato.setTelefono(telefono);

        candidato.setUtente(utente);

        candidatoDao.save(candidato);
    }
}
