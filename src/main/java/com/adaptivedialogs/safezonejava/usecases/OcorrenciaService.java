package com.adaptivedialogs.safezonejava.usecases;

import com.adaptivedialogs.safezonejava.entities.Ocorrencia;
import com.adaptivedialogs.safezonejava.repositories.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository repository;

    public List<Ocorrencia> listarTodas() {
        return repository.findAll();
    }

    public Ocorrencia salvar(Ocorrencia ocorrencia) {
        return repository.save(ocorrencia);
    }

    public Ocorrencia buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
