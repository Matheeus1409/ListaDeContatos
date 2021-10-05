package br.com.matheuscunha;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatosControle {
	
	
	private static final ArrayList<contato> LISTA_CONTATOS = new ArrayList<>();
		
	static {
		LISTA_CONTATOS.add(new contato("João", "1", "+55 11 99999-9999"));
	}

	@GetMapping("/")
	public String paginaInicial() {
		return "index";
	}
	
	@GetMapping("/contatos")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView ("listar");
		
		modelAndView.addObject("contatos" , LISTA_CONTATOS);
		
		return modelAndView;
	}
	
	
	@GetMapping("/contatos/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView ("formulario");
		
		modelAndView.addObject("contato" , new contato());
		
		return modelAndView;
	}
	
	@PostMapping ("/contatos")
	public String cadastrar (contato contatos) {
		String id = UUID.randomUUID().toString();
		
		contatos.setId(id);
		
		LISTA_CONTATOS.add(contatos);
		return "redirect:/contatos";
	}
	
	
	@GetMapping ("/contatos/{id}/editar")
	public ModelAndView editar(@PathVariable String id) {
		
		ModelAndView modelAndView = new ModelAndView ("formulario");
		
		contato contato = procurarContato(id);
		
		
		modelAndView.addObject("contato" ,  contato );
	
		return modelAndView;
		
	}
	
	@PostMapping("/contatos/{id}")
	public String atualizar(contato contato) {
		Integer indice = procurarIndiceContato(contato.getId());
		
		contato contatoVelho = LISTA_CONTATOS.get(indice);
		
		LISTA_CONTATOS.remove(contatoVelho);
		LISTA_CONTATOS.add(indice, contato);
		
		return "redirect:/contatos";
		
	}
	
	@DeleteMapping("/contatos/{id}")
	public String remover(@PathVariable String id) {
		contato contato = procurarContato (id);
		
		LISTA_CONTATOS.remove(contato);
		
		return "redirect:/contatos";
	}
	
	//-----------------------Metodos auxiliares
	
	
	private contato procurarContato(String id) {
		Integer indice = procurarIndiceContato (id);
		
		if (indice != null) {
			contato contato = LISTA_CONTATOS.get(indice);
			return contato;
		}
		return null;
	}

	private Integer procurarIndiceContato(String id) {
		for (int i = 0; i < LISTA_CONTATOS.size(); i++) {
		contato contato = LISTA_CONTATOS.get(i);
		
			if (contato.getId().equals(id)) {
				return i;
		}
	}
		return null;
	}
}