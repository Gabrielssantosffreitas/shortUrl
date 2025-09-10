package com.gabriel.EncurtadorDelinkSimples.services;

import com.gabriel.EncurtadorDelinkSimples.DTO.LinkDTO;
import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import com.gabriel.EncurtadorDelinkSimples.repositorys.LinksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkCreatorService {
    private final LinksRepository linksRepository;

    public LinkCreatorService(LinksRepository linksRepository) {
        this.linksRepository = linksRepository;
    }

    public ResponseEntity<LinkEntity> created (LinkDTO link){

        return ResponseEntity.ok().body(linksRepository.save(LinkEntity
                .builder()
                .url(link.url())
                .shortUrl(link.shortUrl())
                .dateExpire(LocalDateTime.now().plusWeeks(1))
                .createdDate(LocalDateTime.now())
                .build()));

    }
}
