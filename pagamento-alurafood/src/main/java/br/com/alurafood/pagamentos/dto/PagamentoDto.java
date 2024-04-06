package br.com.alurafood.pagamentos.dto;

import br.com.alurafood.pagamentos.model.enums.Status;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PagamentoDto(Long id, BigDecimal valor, String nome, String numero, String expiracao, String codigo,
                           Status status, Long pedidoId, Long formaDePagamentoId) {
}
