package com.gabriel.EncurtadorDelinkSimples.Entitys;

import com.gabriel.EncurtadorDelinkSimples.enums.EstateAtivoDesetivado;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false, unique = true)
    private String shortUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime dateExpire;
    @Column(nullable = false)
    private EstateAtivoDesetivado estate;

    @Column(nullable = false)
    private Long click;


}
