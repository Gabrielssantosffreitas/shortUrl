package com.gabriel.EncurtadorDelinkSimples.services;

import com.gabriel.EncurtadorDelinkSimples.componets.ConnetApiHttpComponent;
import com.gabriel.EncurtadorDelinkSimples.repositorys.LinksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

/*
TODO  TRATAR OS DADOS VINDO DA API GEMMINI GOOGLE
 */

@Service
public class RelatorioIAService {
    private LinksRepository repository;
    private ConnetApiHttpComponent connetApiHttpComponent;

    public RelatorioIAService(LinksRepository repository, ConnetApiHttpComponent connetApiHttpComponent) {
        this.repository = repository;
        this.connetApiHttpComponent = connetApiHttpComponent;
    }

    public ResponseEntity<String> relatorio () {

        HttpResponse<String> response = connetApiHttpComponent.ConnetHttp(repository.findAll().toString());
        return ResponseEntity.ok().body(response.body().);
    }



}
