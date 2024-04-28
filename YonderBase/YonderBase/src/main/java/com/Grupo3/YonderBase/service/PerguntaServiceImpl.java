package com.Grupo3.YonderBase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Grupo3.YonderBase.model.Pergunta;
import com.Grupo3.YonderBase.repository.PerguntaRepository;


@Service
public class PerguntaServiceImpl implements PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Override
    public List<Pergunta> getAllPerguntas() {
        return perguntaRepository.findAll();
    }

    @Override
    public void deletePergunta(Long id) {
        perguntaRepository.deleteById(id);
    }

    @Override
    public Pergunta addPergunta(Pergunta pergunta) {
        return perguntaRepository.save(pergunta);
    }
}
