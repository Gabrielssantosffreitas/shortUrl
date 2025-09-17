package com.gabriel.EncurtadorDelinkSimples.controllers;

import com.gabriel.EncurtadorDelinkSimples.services.RelatorioIAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ia")
public class RelatorioIAController {

    private RelatorioIAService service;

    public RelatorioIAController(RelatorioIAService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<String> relatorio (){
        return service.relatorio();
    }

}