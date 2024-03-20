package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.io.*;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PetServiceTest {
    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private PetService petService = new PetService(client);

    Pet pet = new Pet("Cachorro", "Rex", "Vira-lata", 3, "Castanho", 5.0f);

    @Test
    public void deveVerificarQuandoHaAbrigo() throws IOException, InterruptedException {
        pet.setId(0L);
        String expectedPetsCadastrados = "Pets cadastrados:";
        String expectedPetToString = "0 - Cachorro - Rex - Vira-lata - 3 ano(s)";

        String idOuNome = "0";
        InputStream in = new ByteArrayInputStream(idOuNome.getBytes());
        System.setIn(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[{" + pet.toString() + "}]");
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);

        petService.listarPets();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualPetsCadastrados = lines[1];
        String actualPetToString = lines[2];

        Assertions.assertEquals(expectedPetsCadastrados, actualPetsCadastrados);
        Assertions.assertEquals(expectedPetToString, actualPetToString);
    }

    @Test
    public void deveVerificarQuandoNaoHaAbrigo() throws IOException, InterruptedException {
        pet.setId(0L);
        String expected = "Nenhum pet cadastrado.";

        String idOuNome = "0";
        InputStream in = new ByteArrayInputStream(idOuNome.getBytes());
        System.setIn(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[]");
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);

        petService.listarPets();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[1];

        Assertions.assertEquals(expected, actual);
    }
}
