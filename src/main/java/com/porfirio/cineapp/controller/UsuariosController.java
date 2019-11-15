package com.porfirio.cineapp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.porfirio.cineapp.model.Rol;
import com.porfirio.cineapp.model.Usuario;
import com.porfirio.cineapp.service.IRolesService;
import com.porfirio.cineapp.service.IUsuariosService;
import com.porfirio.cineapp.service.UsuariosService;

@Controller
@RequestMapping(value="/usuarios")
public class UsuariosController {
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private IRolesService rolesService;
	@Autowired
	private IUsuariosService usuariosService;
	
	@GetMapping(value="/create")
	public String crear(@ModelAttribute Usuario usuario, Model model) {
		List<Rol> roles = rolesService.getTodos();
		model.addAttribute("roles", roles);
		return "usuarios/formUsuario";
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		List<Usuario> usuarios = usuariosService.getTodos();
		
		model.addAttribute("usuarios", usuarios);
		return "usuarios/listUsuarios";
	}
	
	@PostMapping(value="/save")
	public String guardar(@ModelAttribute Usuario usuario, @RequestParam ("rolesX") String roles) {
		String[] rol = roles.split("-");
		Rol r = new Rol();
		r.setId(Integer.parseInt(rol[0]));
		r.setNombre(rol[1]);
		Set<Rol> ro = new HashSet<>();
		ro.add(r);
		usuario.setRoles(ro);
		String passtmp = usuario.getPassword();
		String passEncrip = encoder.encode(passtmp);
		usuario.setPassword(passEncrip);
		
		usuariosService.guardar(usuario);
		
		return "redirect:/usuarios/index";
	}
}
