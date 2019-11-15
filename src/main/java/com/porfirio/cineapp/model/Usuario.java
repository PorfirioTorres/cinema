package com.porfirio.cineapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int id;
	private String nombre;
	private String email;
	private String password;
	private int estatus;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="usuarios_roles", 
				joinColumns= {@JoinColumn(name="idusuario", referencedColumnName = "id_usuario")},
				inverseJoinColumns= {@JoinColumn(name="idrol", referencedColumnName = "id_rol")}
	)
	private Set<Rol> roles = new HashSet<>();
	
	public Usuario() {}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	
}
