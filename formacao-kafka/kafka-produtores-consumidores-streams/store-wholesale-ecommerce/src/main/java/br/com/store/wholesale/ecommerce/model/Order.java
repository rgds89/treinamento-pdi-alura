package br.com.store.wholesale.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Order {
    private String userId;
    private String orderId;
    private BigDecimal amount;
}
