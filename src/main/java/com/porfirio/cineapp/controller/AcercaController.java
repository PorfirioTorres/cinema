package com.porfirio.cineapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/acerca")
public class AcercaController {
	
	@GetMapping(value="")
	public String acercaV() {
		return "acerca";
	}
}
