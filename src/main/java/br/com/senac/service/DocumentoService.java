package br.com.senac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.model.Documento;
import br.com.senac.repository.DocumentoRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository dr;

	public Iterable<Documento> buscarPorTodosDocumentos() {
		Iterable<Documento> documentos = dr.findAll();
		return documentos;
	}

	public Documento DocumentoSalvar(Documento documento) {
		return dr.save(documento);
	}

	public Documento buscarPorId(long id) throws ObjectNotFoundException {
		java.util.Optional<Documento> documento = dr.findById(id);
		return documento.orElseThrow(() -> new ObjectNotFoundException(" Documento não encontrado. id " + id));
	}

	public Documento salvarAlteracao(Documento documentoAlterado) throws ObjectNotFoundException {
		Documento documento = buscarPorId(documentoAlterado.getId());
		documento.setId(documentoAlterado.getId());
		documento.setPlaca(documentoAlterado.getPlaca());
		documento.setModelo(documentoAlterado.getModelo());
		return DocumentoSalvar(documento);
	}

	public void exlcuir(long id) {
		dr.deleteById(id);
	}
}
