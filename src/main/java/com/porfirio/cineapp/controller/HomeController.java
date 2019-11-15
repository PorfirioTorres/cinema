package com.porfirio.cineapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.porfirio.cineapp.model.Banner;
import com.porfirio.cineapp.model.Horario;
//import com.porfirio.cineapp.model.Noticia;
import com.porfirio.cineapp.model.Pelicula;
import com.porfirio.cineapp.service.IBannersService;
import com.porfirio.cineapp.service.IHorariosService;
import com.porfirio.cineapp.service.INoticiasService;
import com.porfirio.cineapp.service.IPeliculasService;
import com.porfirio.cineapp.util.Utileria;

@Controller
public class HomeController {
	private SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private IPeliculasService peliculasServiceImple;
	@Autowired
	private IBannersService bannersService;
	@Autowired
	private IHorariosService horariosService;
	@Autowired
	private INoticiasService noticiasService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goHome(Model model) {
		List<String> lfechas = Utileria.getNextDays(4);
	
		List<Pelicula> peliculas = null;
		try {
			peliculas = peliculasServiceImple.buscarHoy(dateformatter.parse(dateformatter.format(new Date())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Banner> banners = bannersService.buscarActivos();
		model.addAttribute("noticias", noticiasService.obtner());
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("banners", banners);
		model.addAttribute("fechaBusqueda", dateformatter.format(new Date()));
		model.addAttribute("fechas", lfechas);
		return "home";
	}

	 @RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
	//@RequestMapping(value = "/detail", method = RequestMethod.GET)
	 public String mostrarDetalle(@PathVariable ("id") int id, @PathVariable("fecha") Date fecha ,Model model) {
	//public String mostrarDetalle(@RequestParam("idMovie") int id, @RequestParam("fecha") String fecha, Model model) {
		
		List<Horario> horarios = horariosService.buscarPorIdPelicula(id, fecha);
		model.addAttribute("horarios", horarios);
		model.addAttribute("fechaBusqueda", dateformatter.format(fecha));
		model.addAttribute("pelicula", peliculasServiceImple.buscarPorId(id));
		return "detalle";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String buscar(Model model, @RequestParam String fecha) {
		System.out.println("fecha busqueda " + fecha);
		List<String> lfechas = Utileria.getNextDays(4);
		
		List<Pelicula> peliculas = null;
		try {
			System.out.println("++++++++++++++++++++++++++++----------+++"+dateformatter.parse(fecha));
			peliculas = peliculasServiceImple.buscarPorFecha(dateformatter.parse(fecha));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("fechas", lfechas);
		return "home";
	}
	
	@RequestMapping(value="/formLogin", method=RequestMethod.GET)
	public String mostrarLogin() {
		return "/formLogin";
	}
	
	@InitBinder
	public void dateBinder(WebDataBinder binder) {
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}


}
