package com.Soberon.infraccionservice.Service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Soberon.infraccionservice.Entity.Infraction;
import com.Soberon.infraccionservice.Repository.InfractionRepository;
import com.Soberon.infraccionservice.Service.InfractionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InfractionServiceImpl implements InfractionService {

    private final InfractionRepository repository;

    @Override
    public Infraction save(Infraction infraction) {
        infraction.setEstado("No pagado");
        infraction.setFecha(LocalDateTime.now());
        return repository.save(infraction);
    }

    @Override
    public Infraction getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Infraction> getByDni(String dni) {
        return repository.findByDni(dni);
    }

    @Override
    public Page<Infraction> getAll(int offset, int limit) {
        PageRequest pageable = PageRequest.of(offset, limit);
        return repository.findAll(pageable);
    }

    @Override
    public Infraction annul(Long id) {
        Infraction i = repository.findById(id).orElseThrow();
        i.setEstado("Anulada");
        return repository.save(i);
    }
}
