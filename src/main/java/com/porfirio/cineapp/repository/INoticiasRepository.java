package com.porfirio.cineapp.repository;

//import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.porfirio.cineapp.model.Noticia;

@Repository("INotiRepo")
public interface INoticiasRepository extends JpaRepository<Noticia, Integer> {
	// Basta con definir el prototipo de los metodos, es decir no hay necesidad de escribir una clase
	// que implemente esta interfaz ya que jpa se encarga de generar los metodos funcionales a partir de estos prototipos
	
	public List<Noticia> findTop3ByEstatusOrderByIdDesc(String estatus);
	/*/ select * from noticia where estatus=?;
	public  List<Noticia> findByEstatus(String estatus);
	
	// select * from noticia where fecha=?;
	public List<Noticia> findByFecha(Date fecha);
	
	// select * from noticia where eststus=? and fecha =?
	public List<Noticia> findByEstatusAndFecha(String estatus, Date fecha);
	
	// select * from noticias where fecha between ? and ?
	public List<Noticia> findByFechaBetween (Date fechaInic, Date fechaFin);*/
}
