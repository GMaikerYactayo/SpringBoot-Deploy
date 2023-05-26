package com.example.Ejercicio456.Service;

import com.example.Ejercicio456.domain.Laptop;
import com.example.Ejercicio456.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * IMPLEMENTACIÓN,
 * iImplementa la interfaz UserService y utiliza el repositorio UserRepository para acceder a los datos.
 * En los métodos de UserServiceImpl, se invocan los métodos
 * correspondientes del repositorio para llevar a cabo las operaciones necesarias.
 */
@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;

    private final Logger log = LoggerFactory.getLogger(LaptopServiceImpl.class);

    public LaptopServiceImpl(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    public List<Laptop> findAll() {
        log.info("Executing findAll Laptops");
        return this.laptopRepository.findAll();
    }

    @Override
    public Optional<Laptop> findById(Long id) {
        log.info("Executing findById");
        return this.laptopRepository.findById(id);
    }

    @Override
    public Laptop save(Laptop laptop) {
        log.info("Creating / Updating laptop");
        if (!this.validateLaptop(laptop))
            return null;
        Laptop laptopDB = this.laptopRepository.save(laptop);
        return laptopDB;
    }

    private boolean validateLaptop(Laptop laptop) {
        // laptop null validation
        if (laptop == null) {
            log.warn("Trying to create null laptop");
            return false;
        }
        return true;
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting car by id");
        if (id == null || id < 0 || id == 0) {
            log.warn("Trying to delete car with wrong id");
            return;
        }
        try {
            this.laptopRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error trying to delete laptop by id {}", id, e);
        }
    }

    @Override
    public void deleteAll() {
        log.info("Deleting cars");
        this.laptopRepository.deleteAll();
    }

}
