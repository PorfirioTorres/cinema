package com.porfirio.cineapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.porfirio.cineapp.model.Horario;
import com.porfirio.cineapp.service.IHorariosService;
import com.porfirio.cineapp.service.IPeliculasService;

@Controller
@RequestMapping(value="/horarios")
public class HorariosController {
		
	@Autowired
	IPeliculasService peliculasService;
	@Autowired
	IHorariosService horariosService;
	
	@GetMapping(value="/indexPaginate")
	public String listado(Model model, Pageable page) {
		Page<Horario> horarios = horariosService.buscarTodos(page);
		model.addAttribute("horarios", horarios);
		return "horarios/listHorarios";
	}
	
	@GetMapping(value = "/create")
	public String crear(@ModelAttribute Horario horario, Model model) {
		model.addAttribute("peliculas", peliculasService.buscarTodas());
		return "horarios/formHorario";
	}
		
	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute Horario horario, BindingResult result, RedirectAttributes attributes) {				
		if (result.hasErrors()) {
			return "horarios/formHorario";
		} else {
			//System.out.println("Horario: " + horario);
			horariosService.guardar(horario);
			attributes.addFlashAttribute("mensaje", "Registro Guardado");
			return "redirect:/horarios/indexPaginate?page=0";
		}
		
	}
	
	@GetMapping(value="/edit/{id}")
	public String editHorario(@PathVariable ("id") int id, Model model) {
		model.addAttribute("peliculas", peliculasService.buscarTodas());
		Horario horario = horariosService.getHorario(id);
		model.addAttribute("horario", horario);
		return "horarios/formHorario";
	}
	
	@GetMapping(value="/delete/{id}")
	public String borrarHorario(@PathVariable ("id") int id, RedirectAttributes attributes) {
		horariosService.borrar(id);
		attributes.addFlashAttribute("mensaje", "El horario fue eliminado");
		return "redirect:/horarios/indexPaginate?page=0";
	}
	
	// Ejercicio: Crear metodo para personalizar el Data Binding para las todas las propiedades de tipo Date
	@InitBinder
	public void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}

