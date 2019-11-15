package com.porfirio.cineapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.porfirio.cineapp.model.Noticia;
import com.porfirio.cineapp.service.INoticiasService;

@Controller
@RequestMapping(value="/noticias")
public class NoticiasController {
	@Autowired
	private INoticiasService noticiasService;
	
	@GetMapping(value="/create")
	public String crear(@ModelAttribute Noticia noticia, Model model) {
		return "noticias/formNoticia";
	}
	
	@PostMapping(value="/save")
	public String guardar(@ModelAttribute Noticia noticia, BindingResult result) {
		if (result.hasErrors()) {
			return "noticias/formNoticia";
		} else {
			noticiasService.guardar(noticia);
			return "redirect:/";
		}
		
	}
	
	@InitBinder
	public void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
