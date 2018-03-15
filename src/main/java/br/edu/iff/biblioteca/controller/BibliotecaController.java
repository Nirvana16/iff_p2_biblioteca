package br.edu.iff.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BibliotecaController {
	
	@RequestMapping("")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", ".   Senha ou Usuario incorretos.");
		if (logout != null)
			model.addAttribute("message", "Login efetuado com sucesso.");
		return "login";
	}

}
