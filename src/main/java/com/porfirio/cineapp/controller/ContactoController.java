package com.porfirio.cineapp.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.porfirio.cineapp.model.Contacto;
import com.porfirio.cineapp.service.IPeliculasService;

@Controller
public class ContactoController {
	@Autowired
	private IPeliculasService peliculasService;
	
	@GetMapping(value="/contacto")
	public String mostrarFormulario(@ModelAttribute Contacto contacto, Model model) {
		model.addAttribute("generos", peliculasService.buscarGeneros());
		model.addAttribute("tiposNotific", getNotificaciones());
		return "formContacto";
	}
	
	@PostMapping(value="/contacto")
	public String guardar(@ModelAttribute Contacto contacto) {
		System.out.println("Contacto: " + contacto);
		return "redirect:/contacto";
	}
	
	private List<String> getNotificaciones() {
		List<String> notific = new LinkedList<String>();
		notific.add("Estrenos");
		notific.add("Promociones");
		notific.add("Noticias");
		notific.add("Preventas");
		return notific;
	}
}
