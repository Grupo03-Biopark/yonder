package projeto.yonder.service;

import java.util.List;

import projeto.yonder.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import projeto.yonder.model.Empresa;

@Service
public class EmpresaService {

    private EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }
}