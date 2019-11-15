package com.porfirio.cineapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class LoginController {
	
	@GetMapping(value="/logout")
	public String logout(HttpServletRequest request){
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	@GetMapping(value="/index")
	public String mostrarPrincipalAdmin(Authentication authentication) {// por aca pasa cuando recien inica sesion
		System.out.println("Sesion de: " + authentication.getName());
		for (GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("Rol: " + rol.getAuthority());
		}
		return "admin";
	}

}
