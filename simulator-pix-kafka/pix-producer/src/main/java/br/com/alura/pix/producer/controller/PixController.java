package br.com.alura.pix.producer.controller;


import br.com.alura.pix.producer.dto.PixDTO;
import br.com.alura.pix.producer.dto.PixStatus;
import br.com.alura.pix.producer.service.PixService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/pix")
@RequiredArgsConstructor
public class PixController {
    private final PixService pixService;

    @PostMapping
    public PixDTO salvarPix(@RequestBody PixDTO pixDTO) {
        PixDTO pix = populatePixDTO(pixDTO);
        return pixService.savePix(pix);
    }

    private PixDTO populatePixDTO(PixDTO pixDTO) {
        return PixDTO.builder()
                .identifier(UUID.randomUUID().toString())
                .transactionDateTime(LocalDateTime.now())
                .status(PixStatus.PROCESSING)
                .build();
    }
}
