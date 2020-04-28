package br.com.senac.controller;

import java.security.Provider.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.model.Fabricante;
import br.com.senac.service.FabricanteService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("fabricante")
public class FabricanteController {

	@Autowired
	FabricanteService fs;

	@GetMapping("/listar")
	public ModelAndView listarFabricantes() {
		ModelAndView mv = new ModelAndView("fabricante/listaFabricante");
		mv.addObject("fabricantes", fs.searchAll());
		return mv;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrarFabricante() {
		ModelAndView mv = new ModelAndView("/fabricante/cadastraFabricante");
		mv.addObject("fabricante", new Fabricante());
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarFabricante(Fabricante fabricante) {
		fs.save(fabricante);
		return listarFabricantes();
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarFabricante(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("fabricante/alteraFabricante");
		mv.addObject("fabricante", fs.search(id));
		return mv;
	}

	@PostMapping("/alterar")
	public ModelAndView alterarFabricante(Fabricante fabricante) throws ObjectNotFoundException {
		fs.edit(fabricante);
		return listarFabricantes();
	}
	
	@GetMapping("/deletar/{id}")
	public ModelAndView deletarFabricante(@PathVariable("id") Integer id) {
		fs.delete(id);
		return listarFabricantes();
	}

}
