package com.gabriel.EncurtadorDelinkSimples.services;

import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import com.gabriel.EncurtadorDelinkSimples.repositorys.LinksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RedirectService {
    private LinksRepository repository;

    public RedirectService(LinksRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Void> redirect (String url){
        LinkEntity linkEntity = repository.findByshortUrl(url).map((LinkEntity l) -> {
            if (l.getDateExpire().isBefore(LocalDateTime.now())) {
                repository.delete(l);
                throw new RuntimeException("esse link Ja expirou");
            }
            return l;
        }).orElseThrow(RuntimeException::new);

         return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(
                 "Location",
                 linkEntity.getUrl()
         )
                 .build();


    }
}
