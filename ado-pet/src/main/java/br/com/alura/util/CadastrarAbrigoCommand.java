package br.com.alura.util;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;

import java.io.IOException;

public class CadastrarAbrigoCommand implements Command {
    ClientHttpConfiguration client = new ClientHttpConfiguration();
    AbrigoService abrigoService = new AbrigoService(client);
    @Override
    public void execute() {
        try {
            abrigoService.cadastrarAbrigo();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
