package com.adaptivedialogs.safezonejava.gateway;

import com.adaptivedialogs.safezonejava.entities.Campanha;
import com.adaptivedialogs.safezonejava.usecases.CampanhaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CampanhaControllerTest {

    @Mock
    private CampanhaService service;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @InjectMocks
    private CampanhaController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        when(service.listarTodas()).thenReturn(Collections.emptyList());
        String view = controller.listar(model);
        verify(model).addAttribute("campanhas", Collections.emptyList());
        assertEquals("campanhas/listar", view);
    }

    @Test
    void testNova() {
        String view = controller.nova(model);
        verify(model).addAttribute(eq("campanha"), any(Campanha.class));
        assertEquals("campanhas/form", view);
    }

    @Test
    void testSalvarComErro() {
        Campanha entity = new Campanha();
        when(result.hasErrors()).thenReturn(true);
        String view = controller.salvar(entity, result);
        assertEquals("campanhas/form", view);
    }

    @Test
    void testSalvarSemErro() {
        Campanha entity = new Campanha();
        when(result.hasErrors()).thenReturn(false);
        String view = controller.salvar(entity, result);
        verify(service).salvar(entity);
        assertEquals("redirect:/campanhas", view);
    }

    @Test
    void testEditar() {
        Long id = 1L;
        Campanha entity = new Campanha();
        when(service.buscarPorId(id)).thenReturn(entity);
        String view = controller.editar(id, model);
        verify(model).addAttribute("campanha", entity);
        assertEquals("campanhas/form", view);
    }

    @Test
    void testDeletar() {
        Long id = 1L;
        String view = controller.deletar(id);
        verify(service).deletar(id);
        assertEquals("redirect:/campanhas", view);
    }
}
