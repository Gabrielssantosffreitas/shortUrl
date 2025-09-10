package com.gabriel.EncurtadorDelinkSimples.controllers;

import com.gabriel.EncurtadorDelinkSimples.DTO.LinkDTO;
import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import com.gabriel.EncurtadorDelinkSimples.services.LinkCreatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LinkCreatorController {

    private  final LinkCreatorService service;

    public LinkCreatorController(LinkCreatorService service) {
        this.service = service;
    }

    @PostMapping("/created")
    public ResponseEntity<LinkEntity> createdLink (@RequestBody LinkDTO link) {return service.created(link);}



}
