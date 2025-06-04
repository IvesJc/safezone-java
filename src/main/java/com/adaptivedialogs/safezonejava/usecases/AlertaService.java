package com.adaptivedialogs.safezonejava.usecases;

import com.adaptivedialogs.safezonejava.entities.Alerta;
import com.adaptivedialogs.safezonejava.entities.Ocorrencia;
import com.adaptivedialogs.safezonejava.repositories.AlertaRepository;
import com.adaptivedialogs.safezonejava.repositories.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository repository;

    public List<Alerta> listarTodos() {
        return repository.findAll();
    }

    public Alerta salvar(Alerta alerta) {
        return repository.save(alerta);
    }

    public Alerta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

