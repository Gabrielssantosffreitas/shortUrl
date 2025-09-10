package com.gabriel.EncurtadorDelinkSimples.controllers;

import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import com.gabriel.EncurtadorDelinkSimples.services.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class RelatorioCrotroller {
    private  final RelatorioService service;

    public RelatorioCrotroller(RelatorioService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LinkEntity>> allDataLinks (){
        return  service.allDataLinks();
    }
    @GetMapping("/gruopByUrl")
    public  ResponseEntity<Map<String,List<LinkEntity>>> gruopByUrl (){
            return  service.gruopByUrl();
    }



}
