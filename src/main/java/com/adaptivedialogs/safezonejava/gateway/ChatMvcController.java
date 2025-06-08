package com.adaptivedialogs.safezonejava.gateway;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ai")
@RequiredArgsConstructor
public class ChatMvcController {

    private final AzureOpenAiChatModel chatModel;

    @GetMapping
    public String mostrarFormulario() {
        return "home/index";
    }

    @PostMapping("/gerar")
    public String gerarResposta(@RequestParam("mensagem") String mensagem, Model model) {
        String resposta = chatModel.call(mensagem);
        model.addAttribute("mensagem", mensagem);
        model.addAttribute("resposta", resposta);
        return "home/index";
    }
}
