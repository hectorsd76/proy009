package es.cic.curso2025.proy009.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso2025.proy009.model.Director;
import es.cic.curso2025.proy009.model.Pelicula;
import es.cic.curso2025.proy009.repository.DirectorRepository;
import es.cic.curso2025.proy009.repository.PeliculaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Transactional
    public Director crearDirectorPelicula(Director director, List<Pelicula> peliculas){


        List<Pelicula> peliculasGuardadas = peliculas.stream()
            .map(pelicula -> {
                pelicula.setAnio(pelicula.getAnio());
                pelicula.setDirector(director);
                pelicula.setGenero(pelicula.getGenero());
                pelicula.setNombre(pelicula.getNombre());
                return peliculaRepository.save(pelicula);
            })
            .collect(Collectors.toList());
        

        director.setPeliculas(peliculasGuardadas);

        return directorRepository.save(director);
    }


    @Transactional(readOnly = true)
    public Director get(Long id){
        Optional<Director> resultado = directorRepository.findById(id);
        return resultado.orElse(null);
    }


    @Transactional(readOnly = true)
    public List<Director> getAll(){
        return directorRepository.findAll();
    }


    @Transactional
    public void delete(Long id){
        directorRepository.deleteById(id);
    }


@Transactional
public Director update(Director director, List<Pelicula> peliculas) {
    Director directorCreado = directorRepository.findById(director.getId()).orElse(null);
    if (directorCreado == null) {
        return null;
    }

    // Actualiza los campos del director
    directorCreado.setNombre(director.getNombre());
    directorCreado.setEdad(director.getEdad());
    directorCreado.setNacionalidad(director.getNacionalidad());
    directorCreado.setNumeroPeliculas(director.getNumeroPeliculas());

    // Asocia cada película al director y la guarda
    List<Pelicula> peliculasActualizadas = peliculas.stream()
        .map(p -> {
            Pelicula peliculaExistente = peliculaRepository.findById(p.getId()).orElse(null);
            if (peliculaExistente != null) {
                peliculaExistente.setNombre(p.getNombre());
                peliculaExistente.setAnio(p.getAnio());
                peliculaExistente.setGenero(p.getGenero());
                peliculaExistente.setDirector(directorCreado);
                return peliculaRepository.save(peliculaExistente);
            } else {
                p.setDirector(directorCreado);
                return peliculaRepository.save(p);
            }
        })
        .collect(Collectors.toList());

    directorCreado.setPeliculas(peliculasActualizadas);

    return directorRepository.save(directorCreado);
}
}
