package com.gabriel.EncurtadorDelinkSimples.controllers;

import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import com.gabriel.EncurtadorDelinkSimples.enums.EstateAtivoDesetivado;
import com.gabriel.EncurtadorDelinkSimples.services.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
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
    @GetMapping("/orderByDate")
    public  ResponseEntity<List<LinkEntity>> orderByDate (){
        return service.orderByDate();
    }
    @GetMapping("/orderByClick")
    public ResponseEntity<List<LinkEntity>>  orderByClick (){
        return  service.orderByClick();
    }
    @GetMapping("/groupByDateCountLink")
    public ResponseEntity<Map<LocalDateTime, Long>> groupByDateCountLink (){
        return  service.groupByDateCountLink();
    }
    @GetMapping("/groupByState")
    private ResponseEntity<Map<EstateAtivoDesetivado, List<LinkEntity>>> groupByState(){
        return  service.groupByState();
    }


}
