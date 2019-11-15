package com.porfirio.cineapp.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.porfirio.cineapp.model.Pelicula;
import com.porfirio.cineapp.service.IDetallesService;
import com.porfirio.cineapp.service.IPeliculasService;
import com.porfirio.cineapp.util.Utileria;

@Controller
@RequestMapping(value = "/peliculas")
public class PeliculasController {
	@Autowired
	private IPeliculasService peliculaServiceImp;
	
	@Autowired
	private IDetallesService detallesServiceImp;
	
	@GetMapping(value = "/index")
	public String mostrarIndex(Model model) {
		List<Pelicula> peliculas = peliculaServiceImp.buscarTodas();
		model.addAttribute("peliculas", peliculas);
		return "peliculas/listPeliculas";
	}
	
	@GetMapping(value="/indexPaginate")
	public String mostrarPaginado(Model model, Pageable page) {
		if (page.getPageNumber() >= 0) {
			Page<Pelicula> peliculas = peliculaServiceImp.buscarTodas(page);
			model.addAttribute("peliculas", peliculas);
		}
		
		return "peliculas/listPeliculas";
	}

	@GetMapping(value = "/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model model) { // se envia un objeto Pelicula al formulario para que trabaje con el
		model.addAttribute("generos", peliculaServiceImp.buscarGeneros());
		return "peliculas/formPelicula";
	}

	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("imagenFile") MultipartFile file, HttpServletRequest request) {// recibe del formulario el objeto que este manipulo y que ya contiene datos
		
		if (result.hasErrors()) {
			// for (ObjectError obj: result.getAllErrors()) { // mostrar todos los errores
			// System.out.println(obj.getDefaultMessage());
			// }
			return "peliculas/formPelicula";
		} else {
			
			if (!file.isEmpty()) {// llego un archivo, validarlo y subirlo
				String nombreImagen = Utileria.guardarImagen(file, request);
				
				if(nombreImagen != null) { // se subio correctamente
					pelicula.setImagen(nombreImagen);
					System.out.println("pelicula: " + pelicula);
					detallesServiceImp.insertar(pelicula.getDetalle());
					peliculaServiceImp.insertar(pelicula);
					attributes.addFlashAttribute("mensaje", "La película fue agregada"); // los atributos flash sobreviven a un
																							// redireccionamiento, despues del																// redireccionamiento son eliminados
					return "redirect:/peliculas/index"; // redireccionar al listado de peliculas
				} else { // no se pudo subir
					System.out.println("Error al subir la imagen");
					return "peliculas/formPelicula";
				}
			} else {
				System.out.println("pelicula: " + pelicula);
				detallesServiceImp.insertar(pelicula.getDetalle());
				peliculaServiceImp.insertar(pelicula);
				attributes.addFlashAttribute("mensaje", "La película fue agregada"); // los atributos flash sobreviven a un
																						// redireccionamiento, despues del															// redireccionamiento son eliminados
				return "redirect:/peliculas/index"; // redireccionar al listado de peliculas
			}
			
		}

	}
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable ("id") int id, Model model) {
		Pelicula pelicula = peliculaServiceImp.buscarPorId(id);
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("generos", peliculaServiceImp.buscarGeneros());
		return "peliculas/formPelicula";
	}
	
	@GetMapping(value="/delete/{id}/{iddetail}")
	public String eliminar(@PathVariable ("id") int id, @PathVariable ("iddetail") int idd, RedirectAttributes attributes) {
		peliculaServiceImp.eliminar(id);
		detallesServiceImp.eliminar(idd);
		attributes.addFlashAttribute("mensaje", "La película fue eliminada");
		return "redirect:/peliculas/index";
	}

	@InitBinder
	public void dateBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}


}
