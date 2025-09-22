package com.gabriel.EncurtadorDelinkSimples.DTO;

import java.util.List;

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

// Record para a Resposta Principal
    public record ResponseGeminiDTO(
            List<Candidate> candidates,
            UsageMetadata usageMetadata,
            String modelVersion,
            String responseId
    ) {}

