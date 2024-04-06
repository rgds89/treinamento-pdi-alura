package br.com.alurafood.pagamentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CadastrarPagamentoDto(@NotNull @Positive BigDecimal valor, @NotBlank @Size(max = 100) String nome,
                                    @NotBlank @Size(max = 19) String numero,
                                    @NotBlank @Size(max = 7) String expiracao, @NotBlank @Size(min = 3, max = 3) String codigo,
                                    @NotNull Long pedidoId, @NotNull Long formaDePagamentoId) {
}
