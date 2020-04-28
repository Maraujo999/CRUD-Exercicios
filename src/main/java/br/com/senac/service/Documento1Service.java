package br.com.senac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.model.Documento1;
import br.com.senac.repository.Documento1Repository;
import javassist.tools.rmi.ObjectNotFoundException;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class Documento1Service {

	@Autowired
	private Documento1Repository dr;

	public Iterable<Documento1> buscarPorTodosDocumentos() {
		Iterable<Documento1> documentos = dr.findAll();
		return documentos;
	}

	public Documento1 DocumentoSalvar(Documento1 documento) {
		return dr.save(documento);
	}

	public Documento1 buscarPorId(long id) throws ObjectNotFoundException {
		java.util.Optional<Documento1> documento = dr.findById(id);
		return documento.orElseThrow(() -> new ObjectNotFoundException(" Documento não encontrado. id " + id));
	}

	public Documento1 salvarAlteracao(Documento1 documentoAlterado) throws ObjectNotFoundException {
		Documento1 documento = buscarPorId(documentoAlterado.getId());
		documento.setId(documentoAlterado.getId());
		documento.setPlaca(documentoAlterado.getPlaca());
		documento.setModelo(documentoAlterado.getModelo());
		return DocumentoSalvar(documento);
	}

	public void exlcuir(long id) {
		dr.deleteById(id);
	}
}
