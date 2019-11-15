package com.porfirio.cineapp.service;

import java.util.List;
import com.porfirio.cineapp.model.Banner;

public interface IBannersService {
	public void insertar(Banner banner); 
	public List<Banner> buscarTodos();
	public List<Banner> buscarActivos();
	public void eliminar(int id);
	public Banner buscarId(int id);
}
