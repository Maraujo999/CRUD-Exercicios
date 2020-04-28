package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.model.Documento;
import br.com.senac.service.DocumentoService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/documento")
public class DocumentoController {

	@Autowired
	DocumentoService ds;

	@GetMapping("/listar")
	public ModelAndView listarDocumento() {
		ModelAndView mv = new ModelAndView("documento/listaDocumento");
		mv.addObject("documentos", ds.searchAll());
		return mv;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrarDocumento() {
		ModelAndView mv = new ModelAndView("documento/cadastraDocumento");
		mv.addObject("documento", new Documento());
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvarDocumento(Documento documento) {
		ds.save(documento);
		return listarDocumento();
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarDocumento(@PathVariable("id") Integer id) throws ObjectNotFoundException {
			ModelAndView mv = new ModelAndView("documento/alteraDocumento");
			mv.addObject("documento", ds.search(id));
			return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterarDocumento(Documento documento) throws ObjectNotFoundException {
		ds.edit(documento);
		return listarDocumento();
	}
	
	@GetMapping("/deletar/{id}")
	public ModelAndView deletarDocumento(@PathVariable("id") Integer id) {
		ds.delete(id);
		return listarDocumento();
	}
	
	

}
