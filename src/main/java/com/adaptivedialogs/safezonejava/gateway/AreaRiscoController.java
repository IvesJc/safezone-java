package com.adaptivedialogs.safezonejava.gateway;

import ch.qos.logback.core.model.Model;
import com.adaptivedialogs.safezonejava.entities.AreaRisco;
import com.adaptivedialogs.safezonejava.usecases.AreaRiscoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/areas-risco")
@RequiredArgsConstructor
public class AreaRiscoController {

    private final AreaRiscoService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("areas", service.listarTodos());
        return "area-risco/listar";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("areaRisco", new AreaRisco());
        return "area-risco/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute AreaRisco areaRisco, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "area-risco/form";
        }
        service.salvar(areaRisco);
        return "redirect:/areas-risco";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("areaRisco", service.buscarPorId(id));
        return "area-risco/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/areas-risco";
    }
}
