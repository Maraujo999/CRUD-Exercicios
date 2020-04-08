package br.com.senac.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.model.Carro;
import br.com.senac.repository.CarroRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarroService {

	@Autowired
	CarroRepository cr;

	public Iterable<Carro> buscarPorTodosCarros() {
		Iterable<Carro> carros = cr.findAll();
		return carros;
	}

	public Carro CarroSalvar(Carro carro) {
		return cr.save(carro);
	}

	public Carro buscarPorId(long id) throws ObjectNotFoundException {
		Optional<Carro> carro = cr.findById(id);
		return carro.orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado. id: " + id));
	}

	public Carro salvarAlteracao(Carro carroAlterado) throws ObjectNotFoundException {
		Carro carro = buscarPorId(carroAlterado.getId());
		carro.setId(carroAlterado.getId());
		carro.setModelo(carroAlterado.getModelo());
		carro.setCor(carroAlterado.getCor());
		return CarroSalvar(carro);
	}
	
	public void excluir(long id) {
		cr.deleteById(id);
	}

}
