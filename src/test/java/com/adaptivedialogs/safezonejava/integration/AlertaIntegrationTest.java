package com.adaptivedialogs.safezonejava.gateway;

import com.adaptivedialogs.safezonejava.entities.Alerta;
import com.adaptivedialogs.safezonejava.entities.Ocorrencia;
import com.adaptivedialogs.safezonejava.repositories.AlertaRepository;
import com.adaptivedialogs.safezonejava.repositories.OcorrenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AlertaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    private Ocorrencia ocorrencia;

    @BeforeEach
    void setUp() {
        alertaRepository.deleteAll();
        ocorrenciaRepository.deleteAll();

        ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao("Enchente grave em área urbana");
        ocorrenciaRepository.save(ocorrencia);

        alertaRepository.save(new Alerta(
                null,
                "Chuva intensa",
                "Volume muito alto de água nas próximas horas",
                LocalDateTime.now().plusHours(1),
                "ALTA",
                ocorrencia
        ));

        alertaRepository.save(new Alerta(
                null,
                "Deslizamento",
                "Risco elevado em encostas instáveis",
                LocalDateTime.now().plusHours(2),
                "CRÍTICA",
                ocorrencia
        ));
    }

    @Test
    void deveListarTodosOsAlertas() throws Exception {
        mockMvc.perform(get("/alertas"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("alertas"))
                .andExpect(model().attribute("alertas", hasSize(2)))
                .andExpect(view().name("alertas/listar"));
    }

    @Test
    void deveSalvarNovoAlerta() throws Exception {
        mockMvc.perform(post("/alertas")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("titulo", "Vento forte")
                        .param("descricao", "Rajadas acima de 80km/h")
                        .param("dataHora", LocalDateTime.now().plusDays(1).toString())
                        .param("severidade", "MODERADA")
                        .param("ocorrencia.id", ocorrencia.getId().toString())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alertas"));

        assertEquals(3, alertaRepository.count());
    }
}
