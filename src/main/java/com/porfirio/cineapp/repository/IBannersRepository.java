package com.porfirio.cineapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.porfirio.cineapp.model.Banner;

public interface IBannersRepository extends JpaRepository<Banner, Integer> {
	public List<Banner> findByEstatus(String estatus);
}
