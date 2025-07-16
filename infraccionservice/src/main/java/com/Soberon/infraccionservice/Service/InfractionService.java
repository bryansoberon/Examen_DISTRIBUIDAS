package com.Soberon.infraccionservice.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.Soberon.infraccionservice.Entity.Infraction;

public interface InfractionService {
    Infraction save(Infraction infraction);
    Infraction getById(Long id);
    List<Infraction> getByDni(String dni);
    Page<Infraction> getAll(int offset, int limit);
    Infraction annul(Long id);
}