package br.com.alurafood.pedidos.amqp;

import br.com.alurafood.pedidos.dto.PagamentoDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {
    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void receberMensagem(PagamentoDto pagamento){
        String mensagem = """
                Dados do Pagamento : %s
                Número do pedido: %s
                Valor R$: %s
                Status: %s
                """.formatted(pagamento.id(), pagamento.pedidoId(), pagamento.valor(), pagamento.status());
        System.out.println("Recebi a mensagem: " + mensagem.toString());
    }
}
