package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestPropertySource(properties = "spring.config.location=classpath:/application-test.yaml")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AdocaoControllerTest {
    @MockBean
    private AdocaoService adocaoService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<SolicitacaoAdocaoDto> jsonDto;

    @Test
    void deveriaDevolverBadRequestQuandoSolicitacaoDeAdocaoComErro() throws Exception {
        String json = "{}";
        var response = mockMvc.perform(
                post("/adocoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andReturn().getResponse();
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCreatedQuandoSolicitacaoDeAdocaoComSucesso() throws Exception {
        SolicitacaoAdocaoDto solicitacaoAdocaoDto = new SolicitacaoAdocaoDto(1L, 1L, "Teste");
        var response = mockMvc.perform(
                post("/adocoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDto.write(solicitacaoAdocaoDto).getJson())
        ).andReturn().getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }

}