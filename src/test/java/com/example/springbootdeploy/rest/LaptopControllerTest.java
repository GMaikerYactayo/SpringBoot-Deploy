package com.example.springbootdeploy.rest;

import com.example.springbootdeploy.Service.LaptopService;
import com.example.springbootdeploy.domain.Laptop;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LaptopService laptopService;

    @InjectMocks
    private LaptopController laptopController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(laptopController).build();
    }

    @Test
    void findAll() throws Exception {
        // Crea una lista de laptops ficticia para simular la respuesta del servicio
        Laptop laptop1 = new Laptop();
        laptop1.setId(1L);
        laptop1.setMarca("marca");
        laptop1.setModelo("modelo");
        laptop1.setPrecio(3D);
        laptop1.setColor("rojo");
        Laptop laptop2 = new Laptop();
        laptop2.setId(2L);
        laptop2.setMarca("marca2");
        laptop2.setModelo("modelo2");
        laptop2.setPrecio(3D);
        laptop2.setColor("azul");
        List<Laptop> laptopList = Arrays.asList(laptop1, laptop2);

        // Mockear el comportamiento del servicio
        Mockito.when(laptopService.findAll()).thenReturn(laptopList); // Cuando se llame a laptopService.findAll(), devuelve la lista de laptops ficticia

        // Realiza la solicitud GET a la ruta /laptops
        mockMvc.perform(MockMvcRequestBuilders.get("/api/laptops"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(laptop1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].marca").value(laptop1.getMarca()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].modelo").value(laptop1.getModelo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].precio").value(laptop1.getPrecio()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].color").value(laptop1.getColor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(laptop2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].marca").value(laptop2.getMarca()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].modelo").value(laptop2.getModelo()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].precio").value(laptop2.getPrecio()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].color").value(laptop2.getColor()));

        // Verifica que el método del servicio haya sido llamado correctamente
        Mockito.verify(laptopService, Mockito.times(1)).findAll(); // Verifica que el método findAll del servicio haya sido llamado exactamente una vez
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;
        Laptop laptop = new Laptop(id, "Marca", "Modelo", 3D, "rojo");

        // Define el comportamiento esperado del servicio
        Mockito.when(laptopService.findById(id)).thenReturn(Optional.of(laptop));

        // Realiza la solicitud GET al endpoint "/users/{id}"
        mockMvc.perform(MockMvcRequestBuilders.get("/api/laptops/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(id.intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.marca", Matchers.is("Marca")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelo", Matchers.is("Modelo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precio", Matchers.is(3D)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color", Matchers.is("rojo")));
        // Verifica que el método del servicio se haya llamado una vez
        Mockito.verify(laptopService, Mockito.times(1)).findById(id);
    }


    @Test
    void create() throws Exception {
        Laptop laptop = new Laptop();
        laptop.setId(null); // Nuevo laptop sin ID existente

        Mockito.when(laptopService.save(Mockito.any(Laptop.class))).thenReturn(laptop);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/laptops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(laptop)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());

        Mockito.verify(laptopService, Mockito.times(1)).save(Mockito.any(Laptop.class));
    }

    @Test
    void update() throws Exception {
        Long laptopId = 1L;
        Laptop laptop = new Laptop();
        laptop.setId(laptopId);

        Mockito.when(laptopService.save(Mockito.any(Laptop.class))).thenReturn(laptop);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/laptops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(laptop)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(laptopId.intValue())));

        Mockito.verify(laptopService, Mockito.times(1)).save(Mockito.any(Laptop.class));
    }


    @Test
    void deleteById() throws Exception {
        Long laptopId = 1L;
        Laptop laptop = new Laptop(laptopId, "Marca", "Modelo", 3D, "rojo");

        Mockito.when(laptopService.save(Mockito.any(Laptop.class))).thenReturn(laptop);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/laptops/{id}", laptopId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(laptopService, Mockito.times(1)).deleteById(laptopId);
    }


    @Test
    void deleteAll() throws Exception {
        Laptop laptop1 = new Laptop();
        laptop1.setId(1L);
        laptop1.setMarca("marca");
        laptop1.setModelo("modelo");
        laptop1.setPrecio(3D);
        laptop1.setColor("rojo");
        Laptop laptop2 = new Laptop();
        laptop2.setId(2L);
        laptop2.setMarca("marca2");
        laptop2.setModelo("modelo2");
        laptop2.setPrecio(3D);
        laptop2.setColor("azul");
        List<Laptop> laptopList = Arrays.asList(laptop1, laptop2);

        // Mockear el comportamiento del servicio
        Mockito.when(laptopService.findAll()).thenReturn(laptopList);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/laptops"))
               .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(laptopService, Mockito.times(1)).deleteAll();
    }

}