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

        for (Pelicula p : peliculas){
            p.setDirector(director);
        }

        director.setPeliculas(peliculas);

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
}
