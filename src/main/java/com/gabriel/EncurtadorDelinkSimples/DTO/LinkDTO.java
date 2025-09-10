package com.gabriel.EncurtadorDelinkSimples.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record LinkDTO(
        @NotBlank
        @URL(message = " is not url")
        String url,
        @NotBlank
        String shortUrl
){
}
