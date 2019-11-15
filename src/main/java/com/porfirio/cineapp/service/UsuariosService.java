package com.porfirio.cineapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porfirio.cineapp.model.Usuario;
import com.porfirio.cineapp.repository.IUsuariosRepository;

@Service
public class UsuariosService implements IUsuariosService{
	@Autowired
	IUsuariosRepository usuariosRepository;
	
	
	@Override
	public void guardar(Usuario usuario) {
		usuariosRepository.save(usuario);
	}


	@Override
	public List<Usuario> getTodos() {
		return usuariosRepository.findAll();
	}


}
