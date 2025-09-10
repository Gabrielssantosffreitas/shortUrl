package com.gabriel.EncurtadorDelinkSimples.services;

import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import com.gabriel.EncurtadorDelinkSimples.repositorys.LinksRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioService {
    private  final LinksRepository repository;

    public RelatorioService(LinksRepository repository) {
        this.repository = repository;
    }


    public ResponseEntity<List<LinkEntity>> allDataLinks (){
        return ResponseEntity
                .ok()
                .body(getLinksNotExpired()
                    .stream()
                    .sorted(Comparator.comparing(LinkEntity::getShortUrl))
                    .toList());
    }
    public  ResponseEntity<Map<String,List<LinkEntity>>> gruopByUrl (){
        return  ResponseEntity.ok()
                    .body(getLinksNotExpired()
                            .stream()
                            .collect(Collectors.groupingBy(LinkEntity::getUrl)));
    }

    private List<LinkEntity> getLinksNotExpired () {

        repository
                .deleteAll(
                        repository
                                .findAll()
                                .stream()
                                .filter((LinkEntity l ) -> l.getDateExpire().isBefore(LocalDateTime.now()))
                                .toList());
        return repository.findAll();
    }
}
