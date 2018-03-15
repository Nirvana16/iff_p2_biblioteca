package br.edu.iff.biblioteca.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.iff.biblioteca.model.Escritor;
import br.edu.iff.biblioteca.repository.Escritores;

@Controller
@RequestMapping("/escritores")
public class EscritoresController {
	
	@Autowired
	private Escritores escritores;
	
	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaEscritores");
		mv.addObject("escritores",escritores.findAll());
		return mv;
	}
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("FrmEscritores");
		mv.addObject(new Escritor());
		return mv;
		
	}
	
	
	@PostMapping("")
	public ModelAndView salvar(@Validated Escritor escritor, Errors erros, RedirectAttributes redirectAttributes){
		ModelAndView mv = new ModelAndView("FrmEscritores");
		mv.addObject("escritores", escritores.findAll());
		if(erros.hasErrors()){
			return mv;
		}
		try{
			this.escritores.save(escritor);
			return new ModelAndView("redirect:escritores");
		}
		catch(Exception e){
			return mv;
		}		
	}	

	@RequestMapping(value ="/excluir/{idEscritor}")
	public String excluirEscritorByPathVariable(@PathVariable Long idEscritor, 
			HttpServletRequest request, HttpServletResponse response) {		
		this.escritores.delete(idEscritor);
		return "redirect:/escritores";
	
	}
	
	@RequestMapping(value ="/alterar/{idEscritor}")
	public ModelAndView alterarEscritorByPathVariable(@PathVariable Long idEscritor, 
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("FrmEscritores");
		mv.addObject("escritores", escritores.findAll());
		Escritor escritor = escritores.findOne(idEscritor);
		mv.addObject(escritor);
		return mv;
	}
	
	
	@RequestMapping(value="{idEscritor}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long idEscritor, RedirectAttributes attributes) {
			escritores.delete(idEscritor);
			attributes.addFlashAttribute("mensagem", "Escritor Removido!");
			return "redirect:/escritores";
		}
}
