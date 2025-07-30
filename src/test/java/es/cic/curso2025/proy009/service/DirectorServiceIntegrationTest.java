package es.cic.curso2025.proy009.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.cic.curso2025.proy009.model.Director;
import es.cic.curso2025.proy009.model.Pelicula;
import es.cic.curso2025.proy009.repository.DirectorRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class DirectorServiceIntegrationTest {
    
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private DirectorService directorService;

    // @BeforeEach
    // public void limpiar(){
    //     directorRepository.deleteAll();
    // }

    @Test
    void crearDirectorYPeliculas(){

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setAnio(2003);
        pelicula1.setGenero("Drama");
        pelicula1.setNombre("Purple");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setAnio(2005);
        pelicula2.setGenero("Comedia");
        pelicula2.setNombre("Jó, que noche");

        List<Pelicula> peliculas = List.of(pelicula1,pelicula2);

        Director director = new Director();
        director.setEdad(20);
        director.setNacionalidad("Española");
        director.setNombre("Héctor");
        director.setNumeroPeliculas(2);

        Director directorGuardado = directorService.crearDirectorPelicula(director,peliculas);

        assertNotNull(directorGuardado.getId());
        System.out.println("ID generado: " + directorGuardado.getId());

    }


    @Test
    void obtenerDirectorPorId(){
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setAnio(2003);
        pelicula1.setGenero("Drama");
        pelicula1.setNombre("Purple");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setAnio(2005);
        pelicula2.setGenero("Comedia");
        pelicula2.setNombre("Jó, que noche");

        List<Pelicula> peliculas = List.of(pelicula1,pelicula2);

        Director director = new Director();
        director.setEdad(20);
        director.setNacionalidad("Española");
        director.setNombre("Héctor");
        director.setNumeroPeliculas(2);

        Director directorGuardado = directorService.crearDirectorPelicula(director,peliculas);
        Long id = directorGuardado.getId();
        directorGuardado = directorService.get(id);

        assertEquals("Española", directorGuardado.getNacionalidad());
        assertEquals("Drama", directorGuardado.getPeliculas().getFirst().getGenero());
        assertEquals(2, directorGuardado.getPeliculas().size());

    }


    @Test
    void obtenerDirectores(){

        // CREO UNA PELICULA PARA EL DIRECTOR 1

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setAnio(2003);
        pelicula1.setGenero("Drama");
        pelicula1.setNombre("Purple");


        List<Pelicula> peliculas1 = List.of(pelicula1);

        Director director1 = new Director();
        director1.setEdad(20);
        director1.setNacionalidad("Española");
        director1.setNombre("Héctor");
        director1.setNumeroPeliculas(2);
        // CREO UNA PELICULA PARA EL DIERCTOR 2


        Pelicula pelicula2 = new Pelicula();
        pelicula2.setAnio(2005);
        pelicula2.setGenero("Comedia");
        pelicula2.setNombre("Jó, que noche");

        List<Pelicula> peliculas2 = List.of(pelicula2);

        Director director2 = new Director();
        director2.setEdad(22);
        director2.setNacionalidad("Español");
        director2.setNombre("Héctors");
        director2.setNumeroPeliculas(23);

        // AÑADO LAS PELICULAS PARA CREAR EN EL DIRECTOR

        directorService.crearDirectorPelicula(director1, peliculas1);
        directorService.crearDirectorPelicula(director2, peliculas2);


         List<Director> directorLista = directorService.getAll(); 

         assertEquals(2, directorLista.size());

    }


    @Test
    void borrarPorId(){

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setAnio(2003);
        pelicula1.setGenero("Drama");
        pelicula1.setNombre("Purple");

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setAnio(2005);
        pelicula2.setGenero("Comedia");
        pelicula2.setNombre("Jó, que noche");

        List<Pelicula> peliculas = List.of(pelicula1,pelicula2);

        Director director = new Director();
        director.setEdad(20);
        director.setNacionalidad("Española");
        director.setNombre("Héctor");
        director.setNumeroPeliculas(2);

        Director directorGuardado = directorService.crearDirectorPelicula(director,peliculas);
        directorService.delete(directorGuardado.getId());

        Director directorEliminado = directorService.get(directorGuardado.getId());

        assertNull(directorEliminado);
    }

    @Test
    void actualizarDirector(){
        
    }
}
