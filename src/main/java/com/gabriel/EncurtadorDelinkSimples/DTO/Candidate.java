package com.gabriel.EncurtadorDelinkSimples.DTO;

// Record para o Candidato
    public record Candidate(
            Content content,
            String role,
            String finishReason,
            double avgLogprobs
    ) {}
