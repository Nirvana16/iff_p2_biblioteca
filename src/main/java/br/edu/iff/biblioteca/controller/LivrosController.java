package br.edu.iff.biblioteca.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.edu.iff.biblioteca.model.Editora;
import br.edu.iff.biblioteca.model.Escritor;
import br.edu.iff.biblioteca.model.Livro;
import br.edu.iff.biblioteca.repository.Editoras;
import br.edu.iff.biblioteca.repository.Escritores;
import br.edu.iff.biblioteca.repository.Livros;

@Controller
@RequestMapping("/livros")
public class LivrosController {
	
	@Autowired
	private Livros livros;
	
	@Autowired
	private Escritores escritor;
	
	@Autowired
	private Editoras editora;
	
	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaLivros");
		List <Livro> lista = livros.findAll();
		mv.addObject("livros",lista);
		mv.addObject(new Livro());
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("FrmLivros");
		List<Escritor> allEscritor = escritor.findAllOrderByEscritor();
		List<Editora> allEditora = editora.findAllOrderByEscritor();
		mv.addObject(new Livro());
		mv.addObject("escritor", allEscritor); 
		mv.addObject("editora", allEditora); 
		return mv;
		
	}
	
	
	@PostMapping("")
	public ModelAndView salvar(@Validated Livro livro, Errors erros, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException{
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file =  multipartRequest.getFile("file");
		livro.setImage(file.getBytes());
		
		ModelAndView mv = new ModelAndView("FrmLivros");
		mv.addObject("livros", livros.findAll());		
		if(erros.hasErrors()){
			return mv;
		}
		try{
			this.livros.save(livro);
			return new ModelAndView("redirect:livros");
		}
		catch(Exception e){
			return mv;
		}		
	}
	
	//Versao curta, talvez precise mudar algo
	
/*	@PostMapping()
	public String salvar(Livro livro, HttpServletRequest request) throws IOException{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file =  multipartRequest.getFile("file");
		livro.setImage(file.getBytes());
		livros.save(livro);
		return "redirect:/livros";		
		
	}*/

	
	// ORIGINAL
	
//	@PostMapping("")
//	public ModelAndView salvar(@Validated Livro livro, Errors erros, RedirectAttributes redirectAttributes){
//		ModelAndView mv = new ModelAndView("FrmLivros");
//		mv.addObject("livros", livros.findAll());
//		if(erros.hasErrors()){
//			return mv;
//		}
//		try{
//			this.livros.save(livro);
//			return new ModelAndView("redirect:livros");
//		}
//		catch(Exception e){
//			return mv;
//		}		
//	}
	
	
	@RequestMapping(value = "/image/{image_id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("image_id") Long imageId) throws IOException {
		byte[] imageContent = livros.findOne(imageId).getImage();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}

	
	@RequestMapping(value ="/excluir/{idLivro}")
	public String excluirLivroByPathVariable(@PathVariable Long idLivro, 
			HttpServletRequest request, HttpServletResponse response) {		
		this.livros.delete(idLivro);
		return "redirect:/livros";	
	}
	
	@RequestMapping(value ="/alterar/{idLivro}")
	public ModelAndView alterarLivroByPathVariable(@PathVariable Long idLivro, 
			HttpServletRequest request, HttpServletResponse response) {
		
		List<Escritor> allEscritor = escritor.findAllOrderByEscritor();
		List<Editora> allEditora = editora.findAllOrderByEscritor();
		ModelAndView mv = new ModelAndView("FrmLivros");
		mv.addObject("livros", livros.findAll());
		Livro livro = livros.findOne(idLivro);
		mv.addObject(livro);
		mv.addObject("escritor", allEscritor); 
		mv.addObject("editora", allEditora); 
		return mv;
	}
	
	@RequestMapping(value="{idLivro}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long idLivro, RedirectAttributes attributes) {
		livros.delete(idLivro);
		attributes.addFlashAttribute("mensagem", "Livro excluído");
		return "redirect:/livros";
	}

}
