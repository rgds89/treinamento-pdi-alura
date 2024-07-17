package br.com.alura.pix.consumer.model;

import br.com.alura.pix.consumer.dto.PixDTO;
import br.com.alura.pix.consumer.dto.PixStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Pix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identifier;
    private String sourceKey;
    private String targetKey;
    private BigDecimal amount;
    private LocalDateTime transactionDateTime;
    @Enumerated(EnumType.STRING)
    private PixStatus status;

    public Pix(PixDTO pixDTO) {
        this.identifier = pixDTO.identifier();
        this.sourceKey = pixDTO.sourceKey();
        this.targetKey = pixDTO.targetKey();
        this.amount = pixDTO.amount();
        this.transactionDateTime = pixDTO.transactionDateTime();
        this.status = pixDTO.status();
    }
}
