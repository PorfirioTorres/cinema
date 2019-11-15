package com.porfirio.cineapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.porfirio.cineapp.model.Banner;
import com.porfirio.cineapp.service.IBannersService;
import com.porfirio.cineapp.util.Utileria;

@Controller
@RequestMapping(value="/banners")
public class BannersController {
	
	@Autowired
	private IBannersService bannerServiceImp;
	
		@GetMapping("/index")
		public String mostrarIndex(Model model) {
			List<Banner> banners = bannerServiceImp.buscarTodos();
			model.addAttribute("banners", banners);
			
			return "banners/listBanners";
		}
		
		@GetMapping("/create")
		public String crear(@ModelAttribute Banner banner) {
			return "banners/formBanner";
		}
		
		@GetMapping(value="/edit/{id}")
		public String editBanner(@PathVariable ("id") int id, Model model) {
			model.addAttribute("banner", bannerServiceImp.buscarId(id));
			return "banners/formBanner";
		}
		
		@GetMapping(value="/delete/{id}")
		public String borrarBanner(@PathVariable ("id") int id) {
			bannerServiceImp.eliminar(id);
			return "redirect:/banners/index";
		}
		
		@PostMapping("/save")
		public String guardar(@ModelAttribute Banner banner, BindingResult result, RedirectAttributes attributes,
				@RequestParam("imagenFile") MultipartFile file, HttpServletRequest request) {
			
			if (result.hasErrors()) {
				// for (ObjectError obj: result.getAllErrors()) { // mostrar todos los errores
				// System.out.println(obj.getDefaultMessage());
				// }
				return "banners/formBanner";
			} else {
				if (!file.isEmpty()) {// llego un archivo, validarlo y subirlo
					String nombreImagen = Utileria.guardarImagen(file, request);
					
					if(nombreImagen != null) { // se subio correctamente
						banner.setArchivo(nombreImagen);
						System.out.println("banner: " + banner);
						bannerServiceImp.insertar(banner);
						attributes.addFlashAttribute("mensaje", "El banner fue agregado"); // los atributos flash sobreviven a un
																								// redireccionamiento, despues del																// redireccionamiento son eliminados
						return "redirect:/banners/index"; // redireccionar al listado de banners
					} else { // no se pudo subir
						System.out.println("Error al subir la imagen");
						return "banners/formBanner";
					}
				} else {
					System.out.println("banner: " + banner);
					bannerServiceImp.insertar(banner);
					attributes.addFlashAttribute("mensaje", "El banner fue agregado"); // los atributos flash sobreviven a un
																							// redireccionamiento, despues del															// redireccionamiento son eliminados
					return "redirect:/banners/index"; // redireccionar al listado de banners
				}
				
			}
			
		}	
}
