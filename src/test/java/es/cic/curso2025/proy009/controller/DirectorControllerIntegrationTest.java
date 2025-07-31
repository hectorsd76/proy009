package es.cic.curso2025.proy009.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.curso2025.proy009.model.Director;
import es.cic.curso2025.proy009.model.Pelicula;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DirectorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearDirectorConPeliculas() throws Exception {
    Pelicula pelicula1 = new Pelicula();
    pelicula1.setNombre("Inception");
    pelicula1.setAnio(2010);
    pelicula1.setGenero("Ciencia Ficción");

    Pelicula pelicula2 = new Pelicula();
    pelicula2.setNombre("Interstellar");
    pelicula2.setAnio(2014);
    pelicula2.setGenero("Ciencia Ficción");

    Director director = new Director();
    director.setNombre("Christopher Nolan");
    director.setEdad(53);
    director.setNacionalidad("Británico");
    director.setNumeroPeliculas(10);
    director.setPeliculas(List.of(pelicula1, pelicula2));

    String directorJson = objectMapper.writeValueAsString(director);
    mockMvc.perform(post("/Director")
            .contentType(MediaType.APPLICATION_JSON)
            .content(directorJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Christopher Nolan"))
            .andExpect(jsonPath("$.peliculas.length()").value(2))
            .andExpect(jsonPath("$.peliculas[0].nombre").value("Inception"))
            .andExpect(jsonPath("$.peliculas[1].nombre").value("Interstellar"));
    }
        
    @Test
    void testGetDirectorPorId() throws Exception {
        // Primero, crea un director con películas
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setNombre("Inception");
        pelicula1.setAnio(2010);
        pelicula1.setGenero("Ciencia Ficción");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setNombre("Interstellar");
        pelicula2.setAnio(2014);
        pelicula2.setGenero("Ciencia Ficción");

        Director director = new Director();
        director.setNombre("Christopher Nolan");
        director.setEdad(53);
        director.setNacionalidad("Británico");
        director.setNumeroPeliculas(10);
        director.setPeliculas(List.of(pelicula1, pelicula2));

        String directorJson = objectMapper.writeValueAsString(director);

        // Crea el director y obtiene el ID de la respuesta
        String response = mockMvc.perform(post("/Director")
                .contentType(MediaType.APPLICATION_JSON)
                .content(directorJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Director directorCreado = objectMapper.readValue(response, Director.class);

        // Ahora prueba el GET por ID
        mockMvc.perform(get("/Director/" + directorCreado.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Christopher Nolan"))
                .andExpect(jsonPath("$.peliculas.length()").value(2))
                .andExpect(jsonPath("$.peliculas[0].nombre").value("Inception"))
                .andExpect(jsonPath("$.peliculas[1].nombre").value("Interstellar"));
    }


    @Test
    void testGetAllDirectores() throws Exception {
        // Crea un director con películas
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setNombre("Inception");
        pelicula1.setAnio(2010);
        pelicula1.setGenero("Ciencia Ficción");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setNombre("Interstellar");
        pelicula2.setAnio(2014);
        pelicula2.setGenero("Ciencia Ficción");

        Director director = new Director();
        director.setNombre("Christopher Nolan");
        director.setEdad(53);
        director.setNacionalidad("Británico");
        director.setNumeroPeliculas(10);
        director.setPeliculas(List.of(pelicula1, pelicula2));

        String directorJson = objectMapper.writeValueAsString(director);

        mockMvc.perform(post("/Director")
                .contentType(MediaType.APPLICATION_JSON)
                .content(directorJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/Director"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Christopher Nolan"))
                .andExpect(jsonPath("$[0].peliculas.length()").value(2))
                .andExpect(jsonPath("$[0].peliculas[0].nombre").value("Inception"))
                .andExpect(jsonPath("$[0].peliculas[1].nombre").value("Interstellar"));
    }


    @Test
    void testDeleteDirector() throws Exception {
        // Crea un director con películas
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setNombre("Inception");
        pelicula1.setAnio(2010);
        pelicula1.setGenero("Ciencia Ficción");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setNombre("Interstellar");
        pelicula2.setAnio(2014);
        pelicula2.setGenero("Ciencia Ficción");

        Director director = new Director();
        director.setNombre("Christopher Nolan");
        director.setEdad(53);
        director.setNacionalidad("Británico");
        director.setNumeroPeliculas(10);
        director.setPeliculas(List.of(pelicula1, pelicula2));

        String directorJson = objectMapper.writeValueAsString(director);

        // Crea el director y obtiene el ID 
        MvcResult result = mockMvc.perform(post("/Director")
                .contentType(MediaType.APPLICATION_JSON)
                .content(directorJson))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Director directorCreado = objectMapper.readValue(response, Director.class);

        // Elimina el director
        mockMvc.perform(delete("/Director/" + directorCreado.getId()))
                .andExpect(status().isOk());

        // Verifica que ya no existe
        mockMvc.perform(get("/Director/" + directorCreado.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }



    @Test
    void testUpdateDirector() throws Exception {
        // Crea un director con películas
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setNombre("Inception");
        pelicula1.setAnio(2010);
        pelicula1.setGenero("Ciencia Ficción");

        Director director = new Director();
        director.setNombre("Christopher Nolan");
        director.setEdad(53);
        director.setNacionalidad("Británico");
        director.setNumeroPeliculas(10);
        director.setPeliculas(List.of(pelicula1));

        String directorJson = objectMapper.writeValueAsString(director);

        // Crea el director y obtiene el ID 
        MvcResult resultado = mockMvc.perform(post("/Director")
                .contentType(MediaType.APPLICATION_JSON)
                .content(directorJson))
                .andExpect(status().isOk())
                .andReturn();

        String resultadoPost = resultado.getResponse().getContentAsString();
        Director directorCreado = objectMapper.readValue(resultadoPost, Director.class);

      
        Pelicula peliculaActualizada = new Pelicula();
        peliculaActualizada.setId(directorCreado.getPeliculas().get(0).getId());
        peliculaActualizada.setNombre("Inception Updated");
        peliculaActualizada.setAnio(2011);
        peliculaActualizada.setGenero("Acción");

        Pelicula nuevaPelicula = new Pelicula();
        nuevaPelicula.setNombre("Dunkirk");
        nuevaPelicula.setAnio(2017);
        nuevaPelicula.setGenero("Bélica");

        Director directorUpdate = new Director();
        directorUpdate.setId(directorCreado.getId());
        directorUpdate.setNombre("Chris Nolan");
        directorUpdate.setEdad(54);
        directorUpdate.setNacionalidad("Británico");
        directorUpdate.setNumeroPeliculas(11);
        directorUpdate.setPeliculas(List.of(peliculaActualizada, nuevaPelicula));

        String directorUpdateJson = objectMapper.writeValueAsString(directorUpdate);

        //  update y obtiene el resultado 
        MvcResult resultadoActualizado = mockMvc.perform(put("/Director/" + directorCreado.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(directorUpdateJson))
                .andExpect(status().isOk())
                .andReturn();

        String updateResultado = resultadoActualizado.getResponse().getContentAsString();
        Director directorActualizado = objectMapper.readValue(updateResultado, Director.class);

        // Validaciones usando el objeto deserializado
        assertEquals("Chris Nolan", directorActualizado.getNombre());
        assertEquals(54, directorActualizado.getEdad());
        assertEquals(11, directorActualizado.getNumeroPeliculas());
        assertEquals(2, directorActualizado.getPeliculas().size());
        assertEquals("Inception Updated", directorActualizado.getPeliculas().get(0).getNombre());
        assertEquals("Dunkirk", directorActualizado.getPeliculas().get(1).getNombre());
    }
}