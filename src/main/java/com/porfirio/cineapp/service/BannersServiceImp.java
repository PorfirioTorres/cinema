package com.porfirio.cineapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porfirio.cineapp.model.Banner;
import com.porfirio.cineapp.repository.IBannersRepository;

@Service
public class BannersServiceImp implements IBannersService {
	@Autowired
	IBannersRepository bannersRepository;

	@Override
	public void insertar(Banner banner) {
		bannersRepository.save(banner);
	}

	@Override
	public List<Banner> buscarTodos() {
		return bannersRepository.findAll();
	}

	@Override
	public void eliminar(int id) {
		bannersRepository.deleteById(id);
	}

	@Override
	public Banner buscarId(int id) {
		Optional<Banner> optional = bannersRepository.findById(id);
		
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Banner> buscarActivos() {
		return bannersRepository.findByEstatus("Activo");
	}

}
