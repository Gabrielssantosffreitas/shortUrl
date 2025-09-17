package com.gabriel.EncurtadorDelinkSimples.services;

import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import com.gabriel.EncurtadorDelinkSimples.enums.EstateAtivoDesetivado;
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
            if (l.getEstate() == EstateAtivoDesetivado.DESATIVO){
                throw new RuntimeException("Esse link nao esta ativo");
            }
            return l;
        }).orElseThrow(RuntimeException::new);

        linkEntity.setClick(linkEntity.getClick()+1);
        repository.save(linkEntity);
         return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(
                 "Location",
                 linkEntity.getUrl()
         )
                 .build();


    }
}
