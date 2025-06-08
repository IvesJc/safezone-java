package com.adaptivedialogs.safezonejava.gateway;

import com.adaptivedialogs.safezonejava.entities.Alerta;
import com.adaptivedialogs.safezonejava.usecases.AlertaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlertaControllerTest {

    @Mock
    private AlertaService service;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @InjectMocks
    private AlertaController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        when(service.listarTodos()).thenReturn(Collections.emptyList());
        String viewName = controller.listar(model);
        verify(model).addAttribute("alertas", Collections.emptyList());
        assertEquals("alertas/listar", viewName);
    }

    @Test
    void testNovo() {
        String view = controller.novo(model);
        verify(model).addAttribute(eq("alerta"), any(Alerta.class));
        assertEquals("alertas/form", view);
    }

    @Test
    void testEditar() {
        Long id = 1L;
        Alerta entity = new Alerta();
        when(service.buscarPorId(id)).thenReturn(entity);
        String view = controller.editar(id, model);
        verify(model).addAttribute("alerta", entity);
        assertEquals("alertas/form", view);
    }

    @Test
    void testDeletar() {
        Long id = 1L;
        String view = controller.deletar(id);
        verify(service).deletar(id);
        assertEquals("redirect:/alertas", view);
    }
}
