package com.example.Ejercicio456.Service;

import com.example.Ejercicio456.domain.Laptop;

import java.util.List;
import java.util.Optional;

/**
 * SERVICIO
 * Capa que contiene la lógica de negocio de una aplicación.
 * Define métodos que representan las operaciones de negocio relacionadas con los usuarios,
 * como obtener un usuario por su ID, obtener todos los usuarios, crear un usuario y eliminar un usuario.
 */
public interface LaptopService {

    List<Laptop> findAll();

    Optional<Laptop> findById(Long id);

    Laptop save(Laptop laptop);

    void deleteById(Long id);

    void deleteAll();

}
