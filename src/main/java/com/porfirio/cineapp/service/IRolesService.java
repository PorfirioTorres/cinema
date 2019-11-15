package com.porfirio.cineapp.service;

import java.util.List;

import com.porfirio.cineapp.model.Rol;

public interface IRolesService {
	public void guardar(Rol rol);
	public List<Rol> getTodos();
}
