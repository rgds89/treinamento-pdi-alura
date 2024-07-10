package  br.com.alurafood.pedidos.dto;


import br.com.alurafood.pedidos.dto.enums.StatusPagamento;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PagamentoDto(Long id, BigDecimal valor, String nome, String numero, String expiracao, String codigo,
                           StatusPagamento status, Long pedidoId, Long formaDePagamentoId) {
}