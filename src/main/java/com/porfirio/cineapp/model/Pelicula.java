package com.porfirio.cineapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.persistence.Transient;

@Entity
@Table(name="peliculas")
public class Pelicula {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private int duracion;
	private String clasificacion;
	private String genero;
	private String imagen;
	private Date fechaEstreno;
	private String estatus;
	
	@OneToOne
	@JoinColumn(name="idDetalle")
	private Detalle detalle;
	
	@OneToMany(mappedBy="pelicula", fetch=FetchType.EAGER) // se indica el atributo que en la otra clase apunta a esta clase
	private List<Horario> horarios;
	
	public Pelicula() {
		imagen = "cinema.png";
		estatus = "Activa";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Date getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Detalle getDetalle() {
		return detalle;
	}

	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	
}
