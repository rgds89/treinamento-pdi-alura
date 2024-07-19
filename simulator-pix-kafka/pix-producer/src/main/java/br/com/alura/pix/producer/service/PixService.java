package br.com.alura.pix.producer.service;

import br.com.alura.pix.producer.dto.PixDTO;
import br.com.alura.pix.producer.model.Pix;
import br.com.alura.pix.producer.respository.PixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PixService {
    @Autowired
    private final PixRepository pixRepository;

    @Autowired
    private final KafkaTemplate<String, PixDTO> kafkaTemplate;

    public PixDTO savePix(PixDTO pixDTO) {
        pixRepository.save(Pix.toEntity(pixDTO));
        kafkaTemplate.send("pix-topic", pixDTO.identifier(), pixDTO);
        return pixDTO;
    }
}
