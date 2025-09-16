package com.gabriel.EncurtadorDelinkSimples.services;
import com.gabriel.EncurtadorDelinkSimples.Entitys.LinkEntity;
import com.gabriel.EncurtadorDelinkSimples.enums.EstateAtivoDesetivado;
import com.gabriel.EncurtadorDelinkSimples.repositorys.LinksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/*
    Observação importante:
    Eu sei que poderia resolver a maior parte dessas operações diretamente com queries no Spring Data JPA,
    inclusive de forma mais performática. No entanto, optei por implementar com programação funcional usando
    Stream API do Java de propósito, para treinar esse recurso e também deixar explícito no código que sei
    aplicá-lo em cenários reais.

    Essa escolha foi intencional porque este projeto será usado no meu portfólio, e quero mostrar não apenas
    meu conhecimento em JPA, mas também minha capacidade de trabalhar com coleções e manipulação funcional
    em Java.
*/

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

    public ResponseEntity<List<LinkEntity>> orderByDate (){
        return  ResponseEntity.ok().body(
                getLinksNotExpired().stream()
                        .sorted(Comparator.comparing(LinkEntity::getCreatedDate))
                        .toList()
        );
    }

    public  ResponseEntity<List<LinkEntity>> orderByClick (){
        return ResponseEntity.ok().body(
                getLinksNotExpired().stream()
                        .sorted(Comparator.comparing(LinkEntity::getClick))
                        .toList());
    }
    public ResponseEntity<Map<LocalDateTime, Long>> groupByDateCountLink (){
        return ResponseEntity.ok().body( getLinksNotExpired().stream()
                .collect(
                        Collectors.groupingBy(LinkEntity::getCreatedDate,
                                Collectors.counting())
                ));
    }
    // eu sei que nao e bom usar o .stream().parallel() pois o cuto de saparar em Threds a maior que o de fazer isso, porem e para testar como ele se conporta
    public ResponseEntity<Map<EstateAtivoDesetivado, List<LinkEntity>>> groupByState(){
         return  ResponseEntity.ok().body(getLinksNotExpired()
                .stream()
                .parallel()
                .collect(Collectors.groupingBy(LinkEntity::getEstate)));
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
