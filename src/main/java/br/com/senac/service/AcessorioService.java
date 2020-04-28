package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.model.Acessorio;
import br.com.senac.repository.AcessorioRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AcessorioService {

	@Autowired
	AcessorioRepository ar;

	public Acessorio search(Integer id) throws ObjectNotFoundException {
		Optional<Acessorio> acessorio = ar.findById(id);
		return acessorio.orElseThrow(
				() -> new ObjectNotFoundException("não encontrado. id" + id + ", Tipo!" + Acessorio.class.getName()));

	}

	public List<Acessorio> searchAll() {
		return ar.findAll();
	}

	public Acessorio save(Acessorio acessorio) {
		return ar.save(acessorio);
	}

	public List<Acessorio> saveAll(List<Acessorio> acessorios) {
		return ar.saveAll(acessorios);
	}

	public Acessorio edit(Acessorio acessorio) throws ObjectNotFoundException {
		Acessorio acessorioAntigo = search(acessorio.getId());
		acessorioAntigo.setId(acessorio.getId());
		acessorioAntigo.setNome(acessorio.getNome());
		return save(acessorioAntigo);

	}

	public void delete(Integer id) {
		ar.deleteById(id);
	}
}
