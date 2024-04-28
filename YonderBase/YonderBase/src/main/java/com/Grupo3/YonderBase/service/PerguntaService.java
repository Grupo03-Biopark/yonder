package com.Grupo3.YonderBase.service;

import java.util.List;

import com.Grupo3.YonderBase.model.Pergunta;

public interface PerguntaService {
    List<Pergunta> getAllPerguntas();
    void deletePergunta(Long id);
    Pergunta addPergunta(Pergunta pergunta);
}
