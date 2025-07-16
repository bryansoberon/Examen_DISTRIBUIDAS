package com.Soberon.infraccionservice.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Soberon.infraccionservice.Entity.Infraction;
import com.Soberon.infraccionservice.Repository.InfractionRepository;


@RestController
@RequestMapping("/api/infracciones")
public class InfractionController {

    @Autowired
    private InfractionRepository infractionRepository;


    @PostMapping
    public ResponseEntity<Infraction> registrar(@RequestBody Infraction infraction) {
        return new ResponseEntity<>(infractionRepository.save(infraction), HttpStatus.CREATED);
    }

 
    @GetMapping
    public List<Infraction> listarPaginado(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return infractionRepository.findAll(pageable).getContent();
    }


   
    @GetMapping("/{id}")
    public ResponseEntity<Infraction> obtenerPorId(@PathVariable("id") Long id) {
        return infractionRepository.findById(id)
                .map(inf -> new ResponseEntity<>(inf, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

 
    @GetMapping("/usuario/{dni}")
    public ResponseEntity<List<Infraction>> obtenerPorDni(@PathVariable("dni") String dni) {
        List<Infraction> lista = infractionRepository.findByDni(dni);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/anular")
    public ResponseEntity<Infraction> anularInfraccion(@PathVariable("id") Long id) {
        return infractionRepository.findById(id)
                .map(existing -> {
                    existing.setEstado("Anulada");
                    return new ResponseEntity<>(infractionRepository.save(existing), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}