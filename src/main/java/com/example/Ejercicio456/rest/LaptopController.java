package com.example.Ejercicio456.rest;

import com.example.Ejercicio456.Service.LaptopService;
import com.example.Ejercicio456.domain.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * CONTROLADOR
 * Es responsable de manejar las solicitudes HTTP relacionadas con los usuarios.
 * Utiliza el Servicio para ejecutar las operaciones necesarias
 * y devuelve las respuestas correspondientes.
 */
@RestController
@RequestMapping("/api")
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @GetMapping("/laptops")
    public List<Laptop> findAll() {
        log.info("REST request to find all laptop");
        return this.laptopService.findAll();
    }

    @GetMapping("/laptops/{id}")
    public ResponseEntity<Laptop> findById(@PathVariable Long id) {
        log.info("REST request to find one laptop");
        Optional<Laptop> laptopOPT = this.laptopService.findById(id);
        if (laptopOPT.isPresent()) {
            return ResponseEntity.ok(laptopOPT.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        log.info("REST request to create a new laptop");
        if (laptop.getId() != null) {
            log.warn("Trying to create a new laptop with existent id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.laptopService.save(laptop));
    }

    @PutMapping("/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        log.info("REST request to find all laptop");
        if (laptop.getId() == null){
            log.warn("Trying to update an existing laptop without id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(this.laptopService.save(laptop));
    }

    @DeleteMapping("/laptops/{id}")
    public ResponseEntity<Laptop> deleteById(@PathVariable Long id) {
        log.info("REST request to delete an existing laptop");
        this.laptopService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/laptops")
    public ResponseEntity<Laptop> deleteAll() {
        log.info("REST request to delete all laptop");
        this.laptopService.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
