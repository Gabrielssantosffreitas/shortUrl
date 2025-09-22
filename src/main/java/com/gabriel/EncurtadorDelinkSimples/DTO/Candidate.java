package com.gabriel.EncurtadorDelinkSimples.DTO;

import com.google.gson.annotations.SerializedName;

// Record para o Candidato
    public record Candidate(

        @SerializedName("content_data") Content content,
        @SerializedName("role_name") String role,
        @SerializedName("finish_reason") String finishReason,
        @SerializedName("average_logprobs") double avgLogprobs
) {}
