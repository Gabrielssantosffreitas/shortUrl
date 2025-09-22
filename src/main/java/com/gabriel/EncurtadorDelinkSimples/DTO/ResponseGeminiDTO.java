package com.gabriel.EncurtadorDelinkSimples.DTO;

import java.util.List;

// Record para a Parte do Conteúdo
public record Part(String text) {}

    // Record para o Conteúdo
    public record Content(List<Part> parts) {}

    // Record para os Detalhes do Token
    record TokenDetails(String modality, int tokenCount) {}

    // Record para os Metadados de Uso
    record UsageMetadata(
            int promptTokenCount,
            int candidatesTokenCount,
            int totalTokenCount,
            List<TokenDetails> promptTokensDetails,
            List<TokenDetails> candidatesTokensDetails
    ) {}

    // Record para o Candidato
    public record Candidate(
            Content content,
            String role,
            String finishReason,
            double avgLogprobs
    ) {}

    // Record para a Resposta Principal
    public record ResponseGeminiDTO(
            List<Candidate> candidates,
            UsageMetadata usageMetadata,
            String modelVersion,
            String responseId
    ) {}

