package br.com.alura.util;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;

import java.io.IOException;

public class LIstarPetCommand implements Command{
    ClientHttpConfiguration client = new ClientHttpConfiguration();
    PetService petService = new PetService(client);

    @Override
    public void execute() {
        try {
            petService.listarPets();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
