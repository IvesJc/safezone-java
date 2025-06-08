package com.adaptivedialogs.safezonejava.gateway;

import com.adaptivedialogs.safezonejava.entities.Alerta;
import com.adaptivedialogs.safezonejava.entities.dto.AlertaDto;
import com.adaptivedialogs.safezonejava.producer.AlertaProducer;
import com.adaptivedialogs.safezonejava.repositories.OcorrenciaRepository;
import com.adaptivedialogs.safezonejava.usecases.AlertaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaService service;
    private final AlertaProducer alertaProducer;

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
    public String salvar(@Valid @ModelAttribute Alerta alerta, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "alertas/form";
        }

        AlertaDto dto = new AlertaDto(
                alerta.getId(),
                alerta.getTitulo(),
                alerta.getDescricao(),
                alerta.getDataHora(),
                alerta.getSeveridade()
        );

        alertaProducer.enviarParaFila(dto);
        redirectAttributes.addFlashAttribute("mensagem", "Alerta enviado para processamento.");
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
