package com.porfirio.cineapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porfirio.cineapp.model.Noticia;
import com.porfirio.cineapp.repository.INoticiasRepository;

@Service
public class NoticiasServiceImp implements INoticiasService{
	
	@Autowired
	INoticiasRepository noticiasRepository;
	
	@Override
	public void guardar(Noticia noticia) {
		noticiasRepository.save(noticia);
	}

	@Override
	public List<Noticia> obtner() {
		return noticiasRepository.findTop3ByEstatusOrderByIdDesc("Activa");
	}

}
