package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.model.Carro;
import br.com.senac.service.AcessorioService;
import br.com.senac.service.CarroService;
import br.com.senac.service.ChaveService;
import br.com.senac.service.DocumentoService;
import br.com.senac.service.FabricanteService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	private CarroService cs;
	
	@Autowired
	private FabricanteService fs;

	@Autowired
	private ChaveService chaveService;
	
	@Autowired
	private DocumentoService ds;
	
	@Autowired
	private AcessorioService as;

	@GetMapping("/listar")
	public ModelAndView listarCarros() {
		ModelAndView mv = new ModelAndView("carro/listaCarros");
		mv.addObject("carros", cs.searchAll());
		return mv;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrarCarro() {
		ModelAndView mv = new ModelAndView("carro/cadastraCarro");
		mv.addObject("carro", new Carro());
		mv.addObject("chaves", chaveService.searchAll());
		mv.addObject("fabricantes", fs.searchAll());
		mv.addObject("documentos", ds.searchAll());
		mv.addObject("acessorios", as.searchAll());
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarCarro(Carro carro) {
		cs.save(carro);
		return listarCarros();
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarCarro(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("carro/alteraCarro");
		mv.addObject("carro", cs.search(id));
		mv.addObject("chaves", chaveService.searchAll());
		mv.addObject("documentos", ds.searchAll());
		mv.addObject("fabricantes", fs.searchAll());
		mv.addObject("acessorios", as.searchAll());
		return mv;
	}

	@PostMapping("/alterar")
	public ModelAndView alterarCarro(Carro carro) throws ObjectNotFoundException {
		cs.edit(carro);
		return listarCarros();
	}

	@GetMapping("/deletar/{id}")
	public ModelAndView deletarCarro(@PathVariable("id") Integer id) {
		cs.delete(id);
		return listarCarros();
	}

}
