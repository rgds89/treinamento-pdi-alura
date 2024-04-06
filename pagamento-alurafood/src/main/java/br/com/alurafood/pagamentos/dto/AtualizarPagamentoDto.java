package br.com.alurafood.pagamentos.dto;

import br.com.alurafood.pagamentos.model.enums.Status;

import java.math.BigDecimal;

public record AtualizarPagamentoDto(BigDecimal valor, String nome, String numero, String expiracao, String codigo,
                                    Status status) {
}
