package projeto.yonder.controller;

import projeto.yonder.model.Pergunta;
import projeto.yonder.repository.PerguntaRepository;
import projeto.yonder.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PerguntaController {

    @Autowired
    PerguntaRepository perguntaRepository;
    @Autowired
    PerguntaService perguntaService;

    @GetMapping("/pergunta")
    public String showList(Model model, @RequestParam (defaultValue="0") int page) {
        model.addAttribute("data", perguntaRepository.findAll( PageRequest.of(page, 5, Sort.by(
                Sort.Order.asc("id")))));
        model.addAttribute("currentPage", page);
        return "TelaVisualisarPerguntas";
    }

    @GetMapping("/pergunta/{id}")
    public String showPergunta(@PathVariable Long id, Model model) {
        Pergunta pergunta = perguntaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pergunta inválida"));
        model.addAttribute("pergunta", pergunta);
        return "pergunta";
    }

    @PostMapping("/savePergunta")
    public String saveAddPergunta (Pergunta pergunta) {
        perguntaRepository.save(pergunta);
        return "redirect:/pergunta";
    }


    @DeleteMapping("pergunta/delete/{id}")
    public ResponseEntity<?> excluirPergunta(@PathVariable Long id) {
        try {
            perguntaService.excluirPergunta(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir a pergunta");
        }
    }

    @PostMapping("/pergunta/editar/{id}")
    @ResponseBody
    public String editarPergunta(@PathVariable("id") Long perguntaId, @RequestBody Pergunta pergunta) {
        try {
            perguntaService.editarPergunta(perguntaId, pergunta);
            return "redirect:/pergunta";
        } catch (Exception e) {
            return "redirect:/pergunta";
        }
    }
}