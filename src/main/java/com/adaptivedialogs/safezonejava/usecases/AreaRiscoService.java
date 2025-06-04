package com.adaptivedialogs.safezonejava.usecases;

import com.adaptivedialogs.safezonejava.entities.AreaRisco;
import com.adaptivedialogs.safezonejava.repositories.AreaRiscoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaRiscoService {
    private final AreaRiscoRepository repository;

    public List<AreaRisco> listarTodos() {
        return repository.findAll();
    }

    public AreaRisco buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Área de risco não encontrada"));
    }

    public AreaRisco salvar(AreaRisco areaRisco) {
        return repository.save(areaRisco);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
