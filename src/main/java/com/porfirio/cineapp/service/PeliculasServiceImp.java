package com.porfirio.cineapp.service;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porfirio.cineapp.model.Horario;
import com.porfirio.cineapp.model.Pelicula;
import com.porfirio.cineapp.repository.IHorariosRepository;
import com.porfirio.cineapp.repository.IPeliculasRepository;

@Service
@Transactional
public class PeliculasServiceImp implements IPeliculasService{
	@Autowired
	private IPeliculasRepository peliculasRepository;
	
	@Autowired
	private IHorariosRepository horariosRepository;

	@Override
	public List<Pelicula> buscarHoy(Date hoy) {
		return getPeliculas(hoy);
	}

	@Override
	public Pelicula buscarPorId(int idPelicula) {
		Optional<Pelicula> opcional = peliculasRepository.findById(idPelicula);
		if(opcional.isPresent()) {
			return opcional.get();
		} else {
			return null;
		}
	}

	@Override
	public void insertar(Pelicula pelicula) {
		peliculasRepository.save(pelicula);
	}

	@Override
	public List<String> buscarGeneros() {
		List<String> generos = new LinkedList<>();
		generos.add("Accion");
		generos.add("Aventura");
		generos.add("Clasicas");
		generos.add("Comedia Romantica");
		generos.add("Drama");
		generos.add("Terror");
		generos.add("Infantil");
		generos.add("Accion y Aventura");
		generos.add("Romantica");
		return generos;
	}

	@Override
	public void eliminar(int idPelicula) {
		peliculasRepository.deleteById(idPelicula);
	}

	@Override
	public Page<Pelicula> buscarTodas(Pageable page) {
		return peliculasRepository.findAll(page);
	}

	@Override
	public List<Pelicula> buscarPorFecha(Date fecha) {
		return getPeliculas(fecha);
	}
	
	private List<Pelicula> getPeliculas(Date fecha) {
		List<Horario> horarios = horariosRepository.findByFecha(fecha);
		List<Pelicula> peliculas = null;
		
		if (horarios.size() > 0) {
			peliculas = new LinkedList<>();
			
			for (Horario h: horarios) {
				peliculas.add(h.getPelicula());
			}
		}
		return peliculas;
	}

	@Override
	public List<Pelicula> buscarTodas() {
		return peliculasRepository.findAll();
	}

}
