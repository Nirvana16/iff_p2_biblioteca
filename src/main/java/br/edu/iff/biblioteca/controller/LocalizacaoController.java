package br.edu.iff.biblioteca.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/localizacao")
public class LocalizacaoController {

	@RequestMapping("")
	public String localizacao(){
		return "Localizacao";
	}
}
