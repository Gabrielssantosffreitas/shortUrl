package com.gabriel.EncurtadorDelinkSimples.services;

import com.gabriel.EncurtadorDelinkSimples.DTO.LinkDTO;
import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import com.gabriel.EncurtadorDelinkSimples.enums.EstateAtivoDesetivado;
import com.gabriel.EncurtadorDelinkSimples.repositorys.LinksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LinkCreatorService {
    private final LinksRepository linksRepository;

    public LinkCreatorService(LinksRepository linksRepository) {
        this.linksRepository = linksRepository;
    }

    public ResponseEntity<LinkEntity> created (LinkDTO link){


        Optional<EstateAtivoDesetivado> estateAtivoDesetivado =  EstateAtivoDesetivado.getEstadoByNumber(link.AtivadoDesativado());

        return estateAtivoDesetivado.map(ativoDesetivado -> ResponseEntity.ok().body(linksRepository.save(LinkEntity
                .builder()
                .url(link.url())
                .shortUrl(link.shortUrl())
                .dateExpire(LocalDateTime.now().plusWeeks(1))
                .createdDate(LocalDateTime.now())
                .click(0L)
                .estate(ativoDesetivado)
                .build()))).orElseGet(() -> ResponseEntity.status(422).build());
    }
}
