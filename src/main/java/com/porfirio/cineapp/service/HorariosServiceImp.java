package com.porfirio.cineapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porfirio.cineapp.model.Horario;
import com.porfirio.cineapp.repository.IHorariosRepository;

@Service
public class HorariosServiceImp implements IHorariosService{
	@Autowired
	private IHorariosRepository horariosRepository;

	@Override
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		return horariosRepository.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
	}

	@Override
	public void guardar(Horario horario) {
		horariosRepository.save(horario);
	}

	@Override
	public Page<Horario> buscarTodos(Pageable page) {
		return horariosRepository.findAll(page);
	}

	@Override
	public Horario getHorario(int id) {
		Optional<Horario> horario = horariosRepository.findById(id);
		if (horario.isPresent()) {
			return horario.get();
		} else {
			return null;
		}
	}

	@Override
	public void borrar(int id) {
		horariosRepository.deleteById(id);
	}

}
