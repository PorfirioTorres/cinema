package com.porfirio.cineapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porfirio.cineapp.model.Detalle;
import com.porfirio.cineapp.repository.IDetallesRepository;

@Service
public class DestallesServiceImp implements IDetallesService{
	@Autowired
	IDetallesRepository detallesRepository;
	
	@Override
	public void insertar(Detalle detalle) {
		detallesRepository.save(detalle);
	}

	@Override
	public void eliminar(int idDetalle) {
		detallesRepository.deleteById(idDetalle);
	}

}
