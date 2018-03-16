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


import br.edu.iff.biblioteca.model.Editora;
import br.edu.iff.biblioteca.repository.Editoras;

@Controller
@RequestMapping("/editoras")
public class EditoraController {
	
	@Autowired
	private Editoras editoras;	
		
	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaEditoras");
		mv.addObject("editoras",editoras.findAll());
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("FrmEditoras");
		mv.addObject(new Editora());; 
		return mv;
		
	}
	
	@PostMapping("")
	public ModelAndView salvar(@Validated Editora editora, Errors erros, RedirectAttributes redirectAttributes){
		ModelAndView mv = new ModelAndView("FrmEditoras");
		mv.addObject("editoras", editoras.findAll());
		if(erros.hasErrors()){
			return mv;
		}
		try{
			this.editoras.save(editora);
			return new ModelAndView("redirect:editoras");
		}
		catch(Exception e){
			return mv;
		}		
	}
	

	@RequestMapping(value ="/excluir/{idEditora}")
	public String excluirEditoraByPathVariable(@PathVariable Long idEditora, 
			HttpServletRequest request, HttpServletResponse response) {		
		this.editoras.delete(idEditora);
		return "redirect:/editoras";	
	}
	
	@RequestMapping(value ="/alterar/{idEditora}")
	public ModelAndView alterarEditoraByPathVariable(@PathVariable Long idEditora, 
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("FrmEditoras");
		mv.addObject("editoras", editoras.findAll());
		Editora editora = editoras.findOne(idEditora);
		mv.addObject(editora); 
		return mv;
	}
	
	@RequestMapping(value="{idEditora}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long idEditora, RedirectAttributes attributes) {
		editoras.delete(idEditora);
		attributes.addFlashAttribute("mensagem", "Editora excluída");
		return "redirect:/editoras";
	}

}
