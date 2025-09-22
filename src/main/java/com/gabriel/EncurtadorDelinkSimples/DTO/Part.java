package com.gabriel.EncurtadorDelinkSimples.DTO;

import com.google.gson.annotations.SerializedName;

// Record para a Parte do Conteúdo
public record Part(
        @SerializedName("text") String text) {}
