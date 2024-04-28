package com.Grupo3.YonderBase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Grupo3.YonderBase.model.Pergunta;
import com.Grupo3.YonderBase.service.PerguntaService;

@Controller
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @GetMapping("/perguntas")
    public String getAllPerguntas(Model model) {
        List<Pergunta> perguntas = perguntaService.getAllPerguntas();
        model.addAttribute("perguntas", perguntas);
        return "perguntas";
    }

    @PostMapping("/perguntas")
    public String addPergunta(Pergunta pergunta) {
        perguntaService.addPergunta(pergunta);
        return "redirect:/perguntas";
    }

    @GetMapping("/perguntas/{id}/delete")
    public String deletePergunta(@PathVariable Long id) {
        perguntaService.deletePergunta(id);
        return "redirect:/perguntas";
    }
}
