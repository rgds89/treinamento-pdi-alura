package br.com.alura.util;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;

import java.io.IOException;

public class CadastrarPetCommand implements Command{
    ClientHttpConfiguration client = new ClientHttpConfiguration();
    PetService petService = new PetService(client);

    @Override
    public void execute() {
        try {
            petService.importarPets();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
