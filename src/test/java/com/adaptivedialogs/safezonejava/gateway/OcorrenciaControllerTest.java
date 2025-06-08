package com.adaptivedialogs.safezonejava.gateway;

import com.adaptivedialogs.safezonejava.entities.Ocorrencia;
import com.adaptivedialogs.safezonejava.usecases.OcorrenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OcorrenciaControllerTest {

    @Mock
    private OcorrenciaService service;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @InjectMocks
    private OcorrenciaController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        when(service.listarTodas()).thenReturn(Collections.emptyList());
        String view = controller.listar(model);
        verify(model).addAttribute("ocorrencias", Collections.emptyList());
        assertEquals("ocorrencias/listar", view);
    }

    @Test
    void testNova() {
        String view = controller.nova(model);
        verify(model).addAttribute(eq("ocorrencia"), any(Ocorrencia.class));
        assertEquals("ocorrencias/form", view);
    }

    @Test
    void testSalvarComErro() {
        Ocorrencia entity = new Ocorrencia();
        when(result.hasErrors()).thenReturn(true);
        String view = controller.salvar(entity, result);
        assertEquals("ocorrencias/form", view);
    }

    @Test
    void testSalvarSemErro() {
        Ocorrencia entity = new Ocorrencia();
        when(result.hasErrors()).thenReturn(false);
        String view = controller.salvar(entity, result);
        verify(service).salvar(entity);
        assertEquals("redirect:/ocorrencias", view);
    }

    @Test
    void testEditar() {
        Long id = 1L;
        Ocorrencia entity = new Ocorrencia();
        when(service.buscarPorId(id)).thenReturn(entity);
        String view = controller.editar(id, model);
        verify(model).addAttribute("ocorrencia", entity);
        assertEquals("ocorrencias/form", view);
    }

    @Test
    void testDeletar() {
        Long id = 1L;
        String view = controller.deletar(id);
        verify(service).deletar(id);
        assertEquals("redirect:/ocorrencias", view);
    }
}
