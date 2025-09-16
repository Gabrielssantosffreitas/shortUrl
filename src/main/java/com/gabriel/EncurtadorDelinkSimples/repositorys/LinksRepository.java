package com.gabriel.EncurtadorDelinkSimples.repositorys;

import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinksRepository extends JpaRepository <LinkEntity,Long> {
    Optional<LinkEntity> findByshortUrl(String url);
}
