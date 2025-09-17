package com.gabriel.EncurtadorDelinkSimples.controllers;

import com.gabriel.EncurtadorDelinkSimples.services.RedirectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/r")
@RestController
public class RedirectController {
    private RedirectService service;

    public RedirectController(RedirectService service) {
        this.service = service;
    }

    @GetMapping("/{url}")
    public ResponseEntity<Void> redirect(@PathVariable String url){
        return  service.redirect(url);
    }
}




