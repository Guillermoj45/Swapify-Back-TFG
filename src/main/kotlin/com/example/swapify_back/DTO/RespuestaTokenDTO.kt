package com.example.swapify_back.DTO

import com.fasterxml.jackson.annotation.JsonProperty

data class RespuestaTokenDTO(
    @JsonProperty("estado") val estado: Int,
    @JsonProperty("token") val token: String
)