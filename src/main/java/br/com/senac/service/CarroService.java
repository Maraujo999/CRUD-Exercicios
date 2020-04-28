package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.model.Carro;
import br.com.senac.model.Chave;
import br.com.senac.repository.CarroRepository;
import br.com.senac.repository.ChaveRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarroService {

	@Autowired
	CarroRepository cr;

	public Carro search(Integer id) throws ObjectNotFoundException {
		java.util.Optional<Carro> carro = cr.findById(id);
		return carro.orElseThrow(
				() -> new ObjectNotFoundException("não encontrado. id: " + id + "Tipo! " + Carro.class.getName()));

	}

	public List<Carro> searchAll() {
		return cr.findAll();
	}

	public Carro save(Carro carro) {
		return cr.save(carro);
	}

	public List<Carro> saveAll(List<Carro> carros) {
		return cr.saveAll(carros);
	}

	public Carro edit(Carro carro) throws ObjectNotFoundException {
		Carro carroAntigo = search(carro.getId());
		carroAntigo.setId(carro.getId());
		carroAntigo.setModelo(carro.getModelo());
		carroAntigo.setChave(carro.getChave());
		carroAntigo.setFabricante(carro.getFabricante());
		carroAntigo.setDocumento(carro.getDocumento());
		carroAntigo.setAcessorios(carro.getAcessorios());
		return save(carroAntigo);
	}

	public void delete(Integer id) {
		cr.deleteById(id);
	}
}
