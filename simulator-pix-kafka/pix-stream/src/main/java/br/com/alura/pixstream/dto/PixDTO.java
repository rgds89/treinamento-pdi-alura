package br.com.alura.pixstream.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PixDTO(String identifier, String sourceKey, String targetKey, BigDecimal amount, LocalDateTime transactionDateTime, PixStatus status) {
}