package com.adaptivedialogs.safezonejava.gateway;

import ch.qos.logback.core.model.Model;
import com.adaptivedialogs.safezonejava.entities.Alerta;
import com.adaptivedialogs.safezonejava.entities.Campanha;
import com.adaptivedialogs.safezonejava.usecases.AlertaService;
import com.adaptivedialogs.safezonejava.usecases.CampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/campanhas")
@RequiredArgsConstructor
public class CampanhaController {

    private final CampanhaService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("campanhas", service.listarTodas());
        return "campanhas/listar";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("campanha", new Campanha());
        return "campanhas/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Campanha campanha, BindingResult result) {
        if (result.hasErrors()) {
            return "campanhas/form";
        }
        service.salvar(campanha);
        return "redirect:/campanhas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("campanha", service.buscarPorId(id));
        return "campanhas/form";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/campanhas";
    }
}

