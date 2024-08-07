package br.com.alura.ecommerce.message;

import java.util.UUID;

public class CorrelationId {
    private String id;

    public CorrelationId(String title) {
        id = title + "(" + UUID.randomUUID().toString() + ")";
    }

    @Override
    public String toString() {
        return "CorrelationId{" +
                "id='" + id + '\'' +
                '}';
    }

    public CorrelationId continueWith(String title) {
        return new CorrelationId(id + "-" +title);
    }
}
