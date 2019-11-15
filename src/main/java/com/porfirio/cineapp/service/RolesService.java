package com.porfirio.cineapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porfirio.cineapp.model.Rol;
import com.porfirio.cineapp.repository.IRolesRepository;

@Service
public class RolesService implements IRolesService{
	@Autowired
	private IRolesRepository rolesRepository;

	@Override
	public void guardar(Rol rol) {
		rolesRepository.save(rol);	
	}

	@Override
	public List<Rol> getTodos() {
		return rolesRepository.findAll();
	}
	
}
