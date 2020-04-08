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
	private DocumentoService ds;

	@GetMapping("/listarDocumentos")
	public ModelAndView listarTodosDocumentos() {
		ModelAndView mv = new ModelAndView("documento/listaDocumento");
		mv.addObject("documentos", ds.buscarPorTodosDocumentos());
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
		ds.DocumentoSalvar(documento);
		return listarTodosDocumentos();
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarDocumento(@PathVariable("id") long idDocumento) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("documento/alteraDocumento");
		mv.addObject("documento", ds.buscarPorId(idDocumento));
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Documento documentoAlterado) throws ObjectNotFoundException {
		ds.salvarAlteracao(documentoAlterado);
		return listarTodosDocumentos();
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluirDocumento(@PathVariable("id") long id) {
		ds.exlcuir(id);
		return listarTodosDocumentos();
	}

}
