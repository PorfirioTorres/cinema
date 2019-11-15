package com.porfirio.cineapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porfirio.cineapp.model.Horario;

@Repository("IHoraRepo")
public interface IHorariosRepository extends JpaRepository<Horario, Integer> {
	//public List<Horario> findByFecha(Date fecha);
	
	public List<Horario> findByPelicula_IdAndFechaOrderByHora(int idPelicula, Date Fecha);
	
	@Query("select h from Horario h where h.fecha = :fecha and pelicula.estatus='Activa' group by h.pelicula order by pelicula.id asc")
	public List<Horario> findByFecha(@Param("fecha") Date fecha);
}
