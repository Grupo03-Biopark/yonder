package projeto.Grupo3.YonderBase;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import projeto.yonder.controller.UsuarioController;
import projeto.yonder.model.Usuario;
import projeto.yonder.service.UsuarioService;

public class UsuarioControllerTest {
    
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }
    
    @Test
    public void testMostrarFormularioCadastro() throws Exception {
        mockMvc.perform(get("/usuario/cadastro"))
                .andExpect(status().isOk())
                .andExpect(view().name("TelaCadastroUsuario"))
                .andExpect(model().attributeExists("usuario"));
    }

    @Test
    public void testCadastrarUsuario() throws Exception {
        mockMvc.perform(post("/usuario/cadastro")
                .param("nome", "Test User")
                .param("email", "test@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(usuarioService, times(1)).salvar(any(Usuario.class));
    }

    @Test
    public void testListarUsuarios() throws Exception {
        when(usuarioService.getTop50Usuarios()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/usuario/listar"))
                .andExpect(status().isOk())
                .andExpect(view().name("TelaListarUsuarios"))
                .andExpect(model().attributeExists("usuarios"));

        verify(usuarioService, times(1)).getTop50Usuarios();
    }
}
