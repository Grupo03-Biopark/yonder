package projeto.yonder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import projeto.yonder.model.NivelamentoUsuario;
import projeto.yonder.model.Usuario;
import projeto.yonder.service.NivelamentoUsuarioService;

@Controller
public class NivelamentoUsuarioController {

    @Autowired
    private NivelamentoUsuarioService nivelamentoUsuarioService;

    @GetMapping("/notas")
    public String mostraNota1(Model model) {
        List<NivelamentoUsuario> notas = nivelamentoUsuarioService.findAll();
        model.addAttribute("niveisId", notas);
        return "TelaVisaoProvas";
    }





        @GetMapping("/versefezprova")
    public int verSeUsuarioFoiAvaliado(@PathVariable Long id, @ModelAttribute("nivelamentoUsuario") NivelamentoUsuario nivelamentoUsuario) {
        List<NivelamentoUsuario> nivelamentoUsuarios = nivelamentoUsuarioService.getTop50Usuarios();
        if(nivelamentoUsuarios.isEmpty()){
        return 1;
        }
    }
}


