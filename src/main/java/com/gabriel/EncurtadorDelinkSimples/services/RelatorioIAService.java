package com.gabriel.EncurtadorDelinkSimples.services;

import com.gabriel.EncurtadorDelinkSimples.DTO.Candidate;
import com.gabriel.EncurtadorDelinkSimples.DTO.Content;
import com.gabriel.EncurtadorDelinkSimples.DTO.Part;
import com.gabriel.EncurtadorDelinkSimples.DTO.ResponseGeminiDTO;
import com.gabriel.EncurtadorDelinkSimples.componets.ConnetApiHttpComponent;
import com.gabriel.EncurtadorDelinkSimples.repositorys.LinksRepository;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;

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
        Gson gson = new Gson();
        ResponseGeminiDTO responseGeminiDTO = gson.fromJson(response.body(), ResponseGeminiDTO.class);
        Candidate first = responseGeminiDTO.candidates().getFirst();
        Content content = first.content();
        List<Part> parts = content.parts();
        String text = parts.getFirst().text();


        return ResponseEntity.ok().body(text);
    }



}
