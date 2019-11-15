package com.porfirio.cineapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.porfirio.cineapp.model.Rol;

@Repository
public interface IRolesRepository extends JpaRepository<Rol, Integer> {

}
