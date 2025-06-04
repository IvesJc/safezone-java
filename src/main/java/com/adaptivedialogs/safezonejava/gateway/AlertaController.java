package com.adaptivedialogs.safezonejava.gateway;

import ch.qos.logback.core.model.Model;
import com.adaptivedialogs.safezonejava.entities.Alerta;
import com.adaptivedialogs.safezonejava.entities.Ocorrencia;
import com.adaptivedialogs.safezonejava.usecases.AlertaService;
import com.adaptivedialogs.safezonejava.usecases.OcorrenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alertas", service.listarTodos());
        return "alertas/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("alerta", new Alerta());
        return "alertas/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Alerta alerta, BindingResult result) {
        if (result.hasErrors()) {
            return "alertas/form";
        }
        service.salvar(alerta);
        return "redirect:/alertas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("alerta", service.buscarPorId(id));
        return "alertas/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/alertas";
    }
}
