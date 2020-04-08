package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.model.Carro;
import br.com.senac.service.CarroService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/carro")
public class CrudController {

	@Autowired
	private CarroService cs;

	@GetMapping("/listarCarros")
	public ModelAndView listarTodosCarros() {
		ModelAndView mv = new ModelAndView("Carro/listaCarros");
		mv.addObject("carros", cs.buscarPorTodosCarros());
		return mv;

	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrarCarro() {
		ModelAndView mv = new ModelAndView("Carro/cadastraCarro");
		mv.addObject("carro", new Carro());
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarCarro(Carro carro) {
		cs.CarroSalvar(carro);
		return listarTodosCarros();
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarCarro(@PathVariable("id") long idCarro) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("carro/alteraCarro");
		mv.addObject("carro", cs.buscarPorId(idCarro));
		return mv;
	}

	@PostMapping("/alterar")
	public ModelAndView alterar(Carro carroAlterado) throws ObjectNotFoundException {
		cs.salvarAlteracao(carroAlterado);
		return listarTodosCarros();
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluirAluno(@PathVariable("id") long id) {
		cs.excluir(id);
		return listarTodosCarros();
	}
}
