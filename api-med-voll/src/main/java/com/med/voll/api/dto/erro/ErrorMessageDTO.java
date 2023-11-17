package com.med.voll.api.dto.erro;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
@Builder
public class ErrorMessageDTO {

    private String error;
    private String code;
    private String message;
    @Builder.Default
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private List errorsRequest;
}
