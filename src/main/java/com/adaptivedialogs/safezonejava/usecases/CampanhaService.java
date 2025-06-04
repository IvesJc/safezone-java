package com.adaptivedialogs.safezonejava.usecases;

import com.adaptivedialogs.safezonejava.entities.Campanha;
import com.adaptivedialogs.safezonejava.repositories.CampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampanhaService {

    private final CampanhaRepository repository;

    public List<Campanha> listarTodas() {
        return repository.findAll();
    }

    public Campanha salvar(Campanha campanha) {
        return repository.save(campanha);
    }

    public Campanha buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
