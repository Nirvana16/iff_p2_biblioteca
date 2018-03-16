package br.edu.iff.biblioteca.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contato")
public class ContatoController {

	@RequestMapping("")
	public String contato(){
		return "Contato";
	}
}
