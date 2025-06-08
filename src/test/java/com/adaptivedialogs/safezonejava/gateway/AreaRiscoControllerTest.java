package com.adaptivedialogs.safezonejava.gateway;

import com.adaptivedialogs.safezonejava.entities.AreaRisco;
import com.adaptivedialogs.safezonejava.usecases.AreaRiscoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AreaRiscoControllerTest {

    @Mock
    private AreaRiscoService service;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @InjectMocks
    private AreaRiscoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListar() {
        when(service.listarTodos()).thenReturn(Collections.emptyList());
        String view = controller.listar(model);
        verify(model).addAttribute("areas", Collections.emptyList());
        assertEquals("area-risco/listar", view);
    }

    @Test
    void testNovo() {
        String view = controller.novo(model);
        verify(model).addAttribute(eq("areaRisco"), any(AreaRisco.class));
        assertEquals("area-risco/form", view);
    }

    @Test
    void testSalvarComErro() {
        AreaRisco entity = new AreaRisco();
        when(result.hasErrors()).thenReturn(true);
        String view = controller.salvar(entity, result, model);
        assertEquals("area-risco/form", view);
    }

    @Test
    void testSalvarSemErro() {
        AreaRisco entity = new AreaRisco();
        when(result.hasErrors()).thenReturn(false);
        String view = controller.salvar(entity, result, model);
        verify(service).salvar(entity);
        assertEquals("redirect:/areas-risco", view);
    }

    @Test
    void testEditar() {
        Long id = 1L;
        AreaRisco entity = new AreaRisco();
        when(service.buscarPorId(id)).thenReturn(entity);
        String view = controller.editar(id, model);
        verify(model).addAttribute("areaRisco", entity);
        assertEquals("area-risco/form", view);
    }

    @Test
    void testExcluir() {
        Long id = 1L;
        String view = controller.excluir(id);
        verify(service).excluir(id);
        assertEquals("redirect:/areas-risco", view);
    }
}
