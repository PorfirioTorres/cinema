package com.porfirio.cineapp.service;

import java.util.List;

import com.porfirio.cineapp.model.Usuario;

public interface IUsuariosService {
	public void guardar(Usuario usuario);
	public List<Usuario> getTodos();
}
