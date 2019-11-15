package com.porfirio.cineapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.porfirio.cineapp.model.Pelicula;

public interface IPeliculasService {
	public List<Pelicula> buscarTodas();
	public List<Pelicula> buscarHoy(Date hoy);
	public Pelicula buscarPorId(int idPelicula);
	public void insertar(Pelicula pelicula);
	public List<String> buscarGeneros();
	public void eliminar(int idPelicula);
	public Page<Pelicula> buscarTodas(Pageable page); 
	public List<Pelicula> buscarPorFecha(Date fecha);
}
