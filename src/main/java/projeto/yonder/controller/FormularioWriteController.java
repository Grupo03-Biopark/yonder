package projeto.yonder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projeto.yonder.model.FormularioWrite;
import projeto.yonder.model.Usuario;
import projeto.yonder.service.FormularioWriteService;
import projeto.yonder.service.UsuarioService;

@Controller
public class FormularioWriteController {

    @Autowired
    private FormularioWriteService formularioWriteService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/writing/{id}")
    public String formularioWriteParaUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        FormularioWrite formularioWrite = new FormularioWrite();
        formularioWrite.setUsuario(usuario);
        model.addAttribute("formularioWrite", formularioWrite);
        return "TelaWriting";
    }

    @PostMapping("/writing/{id}")
    public String salvar(@PathVariable Long id, @ModelAttribute("formularioWrite") FormularioWrite formularioWrite) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        formularioWrite.setUsuario(usuario);
        formularioWrite.setCorrigido(false);
        formularioWriteService.save(formularioWrite);
        return "redirect:/correcaowriting/" + usuario.getId();
    }

<<<<<<< HEAD
    @GetMapping("/respostas")
    public String writeningCorrecao(@RequestParam("id") Long id, Model model) {
        FormularioWrite formularioWrite = formularioWriteService.findById(id);
        model.addAttribute("formularioWrite", formularioWrite);
        return "TelaCandidatoRespostas";
    }

    @GetMapping("/correcao")
    public String corrigir(@RequestParam("id") Long id, Model model) {
        FormularioWrite formularioWrite = formularioWriteService.findById(id);
        model.addAttribute("formularioWrite", formularioWrite);
        return "TelaRespostas";
    }

    @PostMapping("/correcao")
    public String corrigir(@ModelAttribute("formularioWrite") FormularioWrite formularioWrite) {
        formularioWrite.setCorrigido(true);
        formularioWriteService.save(formularioWrite);
        return "redirect:/respostas/writing";
    }
=======
>>>>>>> main
}