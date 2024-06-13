package projeto.Grupo3.YonderBase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import projeto.yonder.controller.EmpresaController;
import projeto.yonder.model.Empresa;
import projeto.yonder.repository.EmpresaRepository;
import projeto.yonder.service.EmpresaService;

public class EmpresaControllerTest {

    @Mock
    private EmpresaService empresaService;

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaController empresaController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(empresaController).build();
    }

    @Test
    public void testShowCadastroForm() throws Exception {
        mockMvc.perform(get("/empresas/cadastrarEmpresas"))
            .andExpect(status().isOk())
            .andExpect(view().name("TelaCadastrarEmpresas"));
    }

    @Test
    public void testListarEmpresas() throws Exception {
        when(empresaService.getAllEmpresas()).thenReturn(List.of(new Empresa(), new Empresa()));

        mockMvc.perform(get("/empresas"))
            .andExpect(status().isOk())
            .andExpect(view().name("TelaListarEmpresas"))
            .andExpect(model().attributeExists("empresas"));
    }

    @Test
    public void testCadastrarEmpresa() throws Exception {
        mockMvc.perform(post("/empresas/cadastro")
                .param("razaoSocial", "Empresa Teste")
                .param("cnpj", "12345678000195")
                .param("cep", "12345678")
                .param("logradouro", "Rua Teste")
                .param("bairro", "Bairro Teste")
                .param("numero", "123")
                .param("complemento", "Complemento Teste"))
            .andExpect(status().isOk())
            .andExpect(view().name("TelaCadastroEmpresas"));

        verify(empresaService).save(any(Empresa.class));
    }

    @Test
    public void testExcluirEmpresa() throws Exception {
        mockMvc.perform(post("/empresas/excluir/1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/empresas"));

        verify(empresaService).excluirEmpresa(anyLong());
    }

    @Test
    public void testGerenciarEmpresas() throws Exception {
        mockMvc.perform(get("/empresas/gerenciar-empresas"))
            .andExpect(status().isOk())
            .andExpect(view().name("gerenciarEmpresas"));
    }

    @Test
    public void testUsersCarac() throws Exception {
        mockMvc.perform(get("/empresas/usuariosEmpresa"))
            .andExpect(status().isOk())
            .andExpect(view().name("TelaVisaoUsersEmpresa"));
    }

    @Test
    public void testHome() throws Exception {
        when(empresaRepository.findAll()).thenReturn(List.of(new Empresa(), new Empresa()));

        mockMvc.perform(get("/empresas/telaDaniel"))
            .andExpect(status().isOk())
            .andExpect(view().name("TelaDaniel"))
            .andExpect(model().attributeExists("empresas"));
    }
}
