package com.gabriel.EncurtadorDelinkSimples.repositorys;

import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinksRepository extends JpaRepository <LinkEntity,Long> {
}
