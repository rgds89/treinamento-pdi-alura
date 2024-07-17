package br.com.alura.pix.consumer.service;

import br.com.alura.pix.consumer.dto.PixDTO;
import br.com.alura.pix.consumer.dto.PixStatus;
import br.com.alura.pix.consumer.model.Key;
import br.com.alura.pix.consumer.model.Pix;
import br.com.alura.pix.consumer.repository.KeyRepository;
import br.com.alura.pix.consumer.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PixValidatorService {
    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private PixRepository pixRepository;

    @KafkaListener(topics = "pix-topic", groupId = "grupo")
    public void processaPix(PixDTO pixDTO) {
        System.out.println("Pix  recebido: " + pixDTO.identifier());

        Pix pix = pixRepository.findByIdentifier(pixDTO.identifier());

        Key source = keyRepository.findByKey(pixDTO.sourceKey());
        Key target = keyRepository.findByKey(pixDTO.targetKey());

        if (source == null || target == null) {
            pix.setStatus(PixStatus.ERRO);
        } else {
            pix.setStatus(PixStatus.PROCESSED);
        }
        pixRepository.save(pix);
    }
}
