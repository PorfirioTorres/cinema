package com.porfirio.cineapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.porfirio.cineapp.model.Pelicula;

@Repository("IPeliRepo")
public interface IPeliculasRepository extends JpaRepository<Pelicula, Integer> {

}
