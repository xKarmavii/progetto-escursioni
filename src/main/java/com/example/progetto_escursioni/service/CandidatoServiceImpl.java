package com.example.progetto_escursioni.service;

import com.example.progetto_escursioni.dao.CandidatoDao;
import com.example.progetto_escursioni.model.Candidato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    @Autowired
    private CandidatoDao candidatoDao;

    @Override
    public void salvaCandidato(Candidato candidato) {

        candidatoDao.save(candidato);

    }
}
