package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.model.Acessorio;
import br.com.senac.service.AcessorioService;
import javassist.tools.rmi.ObjectNotFoundException;

@Repository
@RequestMapping("/acessorio")
public class AcessorioController {

	@Autowired
	AcessorioService ar;

	@GetMapping("/listar")
	public ModelAndView listarAcessorios() {
		ModelAndView mv = new ModelAndView("acessorio/listaAcessorio");
		mv.addObject("acessorios", ar.searchAll());
		return mv;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrarAcessorio() {
		ModelAndView mv = new ModelAndView("acessorio/cadastraAcessorio");
		mv.addObject("acessorio", new Acessorio());
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarAcessorio(Acessorio acessorio) {
		ar.save(acessorio);
		return listarAcessorios();
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarAcessorio(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("acessorio/AlteraAcessorio");
		mv.addObject("acessorio", ar.search(id));
		return mv;
	}

	@PostMapping("/alterar")
	public ModelAndView alterarAcessorio(Acessorio acessorio) throws ObjectNotFoundException {
		ar.edit(acessorio);
		return listarAcessorios();
	}

	@GetMapping("/deletar/{id}")
	public ModelAndView deletarAcessorio(@PathVariable("id") Integer id) {
		ar.delete(id);
		return listarAcessorios();
	}
}
