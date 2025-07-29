package es.cic.curso2025.proy009.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso2025.proy009.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {

    
}