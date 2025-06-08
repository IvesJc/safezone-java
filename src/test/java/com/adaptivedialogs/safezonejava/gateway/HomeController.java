package com.adaptivedialogs.safezonejava.gateway;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    @Mock
    private Model model;

    @Mock
    private OAuth2User oauth2User;

    private HomeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new HomeController();
    }

    @Test
    void testHomeComUsuarioAutenticado() {
        when(oauth2User.getAttribute("name")).thenReturn("Ives Jundi");

        String view = controller.home(model, oauth2User);

        verify(model).addAttribute("nome", "Ives Jundi");
        assertEquals("home/index", view);
    }
}
