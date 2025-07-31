package es.cic.curso2025.proy009.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.cic.curso2025.proy009.model.Director;
import es.cic.curso2025.proy009.model.Pelicula;
import es.cic.curso2025.proy009.service.DirectorService;

@RestController
@RequestMapping("/Director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @PostMapping
    public Director crearDirector(@RequestBody Director director) {
        return directorService.crearDirectorPelicula(director, director.getPeliculas());
    }

    @GetMapping("/{id}")
    public Director getDirector(@PathVariable Long id) {
        return directorService.get(id);
    }

    @GetMapping
    public List<Director> getAllDirectores() {
        return directorService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable Long id) {
        directorService.delete(id);
    }

    @PutMapping("/{id}")
    public Director updateDirector(@PathVariable Long id, @RequestBody Director director) {
        director.setId(id);
        return directorService.update(director, director.getPeliculas());
    }


    
}