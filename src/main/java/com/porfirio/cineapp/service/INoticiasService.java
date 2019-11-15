package com.porfirio.cineapp.service;

import java.util.List;

import com.porfirio.cineapp.model.Noticia;

public interface INoticiasService {
	public void guardar(Noticia noticia);
	public List<Noticia> obtner();
}
