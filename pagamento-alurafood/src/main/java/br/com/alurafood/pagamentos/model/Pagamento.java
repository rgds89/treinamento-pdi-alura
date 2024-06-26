package br.com.alurafood.pagamentos.model;

import br.com.alurafood.pagamentos.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Long pedidoId;
    private Long formaDePagamentoId;

    public void cancelar() {
        this.status = Status.CANCELADO;
    }

    public void confirmar() {
        this.status = Status.CONFIRMADO;
    }
}
