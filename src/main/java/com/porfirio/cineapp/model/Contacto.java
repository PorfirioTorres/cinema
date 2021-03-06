package com.porfirio.cineapp.model;

import java.util.Arrays;

public class Contacto {
	private int id;
	private String nombre;
	private String email;
	private int rating;
	private String[] generos; // podria ser un list
	private String[] notificaciones; // podria ser un list
	private String comentarios;
	
	public Contacto() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String[] getGeneros() {
		return generos;
	}

	public void setGeneros(String[] generos) {
		this.generos = generos;
	}

	public String[] getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(String[] notificaciones) {
		this.notificaciones = notificaciones;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Contacto [id=" + id + ", nombre=" + nombre + ", email=" + email + ", rating=" + rating + ", generos="
				+ Arrays.toString(generos) + ", notificaciones=" + Arrays.toString(notificaciones) + ", comentarios="
				+ comentarios + "]";
	}

}
