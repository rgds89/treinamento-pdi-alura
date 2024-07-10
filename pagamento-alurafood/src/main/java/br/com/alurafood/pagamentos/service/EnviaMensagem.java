package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
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

    @Value("${pagamento.exchange.name:pagamento.exchange}")
    private String exchangePagamento;

    public void enviaPagamentoConcluido(PagamentoDto pagamento){
        rabbitTemplate.convertAndSend(exchangePagamento, "", pagamento);
    }
}
