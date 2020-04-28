package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.model.Chave;
import br.com.senac.service.ChaveService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/chave")
public class ChaveController {

	@Autowired
	private ChaveService cs;

	@GetMapping("/listarChaves")
	public ModelAndView listarChaves() {
		ModelAndView mv = new ModelAndView("chave/listaChave");
		mv.addObject("chaves", cs.searchAll());
		return mv;

	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrarChave() {
		ModelAndView mv = new ModelAndView("chave/cadastrarChave");
		mv.addObject("chave", new Chave());
		return mv;
	}

	@GetMapping("/adicionaChave")
	public ModelAndView salvarChave() {
		cs.save(new Chave());
		return listarChaves();
	}

	@GetMapping("/deletar/{id}")
	public ModelAndView deletarChave(@PathVariable("id") Integer id) {
		cs.delete(id);
		return listarChaves();
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarChave(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("chave/alteraChave");
		mv.addObject("chave", cs.search(id));
		return mv;
	}

	@PostMapping("/alterar")
	public ModelAndView alterar(Chave chave) throws ObjectNotFoundException {
		cs.edit(chave);
		return listarChaves();
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarChave(Chave chave) {
		 cs.save(chave);
		return listarChaves();
	}
}
