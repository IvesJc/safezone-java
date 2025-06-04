package com.adaptivedialogs.safezonejava.gateway;

import com.adaptivedialogs.safezonejava.entities.Ocorrencia;
import com.adaptivedialogs.safezonejava.usecases.OcorrenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {

    private final OcorrenciaService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ocorrencias", service.listarTodas());
        return "ocorrencias/listar";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("ocorrencia", new Ocorrencia());
        return "ocorrencias/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Ocorrencia ocorrencia, BindingResult result) {
        if (result.hasErrors()) {
            return "ocorrencias/form";
        }
        service.salvar(ocorrencia);
        return "redirect:/ocorrencias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("ocorrencia", service.buscarPorId(id));
        return "ocorrencias/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/ocorrencias";
    }
}
