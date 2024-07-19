package br.com.alura.pix.producer.model;

import br.com.alura.pix.producer.dto.PixDTO;
import br.com.alura.pix.producer.dto.PixStatus;
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

    public static Pix toEntity(PixDTO pixDTO) {
        Pix pix = new Pix();
        pix.setIdentifier(pixDTO.identifier());
        pix.setSourceKey(pixDTO.sourceKey());
        pix.setTargetKey(pixDTO.targetKey());
        pix.setAmount(pixDTO.amount());
        pix.setTransactionDateTime(pixDTO.transactionDateTime());
        pix.setStatus(pixDTO.status());
        return pix;
    }
}
