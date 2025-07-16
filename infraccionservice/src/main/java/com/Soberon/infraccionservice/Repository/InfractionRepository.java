package com.Soberon.infraccionservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Soberon.infraccionservice.Entity.Infraction;

public interface InfractionRepository extends JpaRepository<Infraction, Long> {
	List<Infraction> findByDni(String dni);
}