package com.porfirio.cineapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.porfirio.cineapp.model.Usuario;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuario, Integer> {
	
}
