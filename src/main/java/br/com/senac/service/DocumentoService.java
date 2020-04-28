package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.model.Documento;
import br.com.senac.repository.DocumentoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class DocumentoService {

	@Autowired
	DocumentoRepository dc;

	public Documento search(Integer id) throws ObjectNotFoundException {

		Optional<Documento> documento = dc.findById(id);

		return documento.orElseThrow(() -> new ObjectNotFoundException(

				"não encontrado. id: " + id + ", Tipo!" + Documento.class.getName()));
	}

	public List<Documento> searchAll() {
		return dc.findAll();
	}

	public Documento save(Documento documento) {
		return dc.save(documento);
	}

	public List<Documento> saveAll(List<Documento> documentos) {
		return dc.saveAll(documentos);
	}

	public Documento edit(Documento documento) throws ObjectNotFoundException {
		Documento documentoAntigo = search(documento.getId());

		documentoAntigo.setId(documento.getId());
		documentoAntigo.setNome(documento.getNome());
		documentoAntigo.setCodigo(documento.getCodigo());

		return save(documentoAntigo);
	}

	public void delete(Integer id) {
		dc.deleteById(id);
	}

}
