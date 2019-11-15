package com.porfirio.cineapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.porfirio.cineapp.model.Horario;
import com.porfirio.cineapp.model.Pelicula;

public interface IHorariosService {
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
	public void guardar(Horario horario);
	public Page<Horario> buscarTodos(Pageable page);
	public Horario getHorario(int id);
	public void borrar(int id);
}
