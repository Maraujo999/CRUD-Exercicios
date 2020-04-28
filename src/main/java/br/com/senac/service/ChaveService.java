package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.model.Chave;
import br.com.senac.repository.ChaveRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ChaveService {

	@Autowired
	ChaveRepository cr;

	public Chave search(Integer id) throws ObjectNotFoundException {
		java.util.Optional<Chave> chave = cr.findById(id);
		return chave.orElseThrow(
				() -> new ObjectNotFoundException("não encontrado. id: " + id + "Tipo! " + Chave.class.getName()));

	}

	public List<Chave> searchAll() {
		return cr.findAll();
	}

	public Chave save(Chave chave) {
		return cr.save(chave);
	}

	public List<Chave> saveAll(List<Chave> chaves) {
		return cr.saveAll(chaves);
	}

	public Chave edit(Chave chave) throws ObjectNotFoundException {
		Chave chaveAntiga = search(chave.getId());
		chaveAntiga.setCodigo(chave.getCodigo());
		return save(chaveAntiga);
	}

	public void delete(Integer id) {
		cr.deleteById(id);
	}

}
