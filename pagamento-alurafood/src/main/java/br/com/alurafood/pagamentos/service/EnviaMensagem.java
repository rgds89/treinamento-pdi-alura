package br.com.alurafood.pagamentos.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnviaMensagem {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${pagamento.concluido.queue.name:pagamento.concluiido}")
    private String queueNamePagConluido;

    public void enviaPagamentoConcluido(Long idPagamento){
        Message msg = new Message(("Criei um pagamento com o id " + idPagamento).getBytes());
        rabbitTemplate.send(queueNamePagConluido, msg);
    }
}
