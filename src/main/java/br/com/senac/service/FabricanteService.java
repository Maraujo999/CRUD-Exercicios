package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.model.Fabricante;
import br.com.senac.repository.FabricanteRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class FabricanteService {

	@Autowired
	FabricanteRepository fr;

	public Fabricante search(Integer id) throws ObjectNotFoundException {
		java.util.Optional<Fabricante> fabricante = fr.findById(id);
		return fabricante.orElseThrow(() -> new ObjectNotFoundException(

				"Não encontrado. id: " + id + ", Tipo!" + Fabricante.class.getName()));
	}

	public List<Fabricante> searchAll() {
		return fr.findAll();

	}

	public Fabricante save(Fabricante fabricante) {
		return fr.save(fabricante);
	}

	public List<Fabricante> saveAll(List<Fabricante> fabricantes) {
		return fr.saveAll(fabricantes);
	}
	
	public Fabricante edit(Fabricante fabricante) throws ObjectNotFoundException {
		Fabricante fabricanteAntigo = search(fabricante.getId());
		fabricanteAntigo.setId(fabricante.getId());
		return save(fabricanteAntigo);
	}
	
	public void delete(Integer id) {
		fr.deleteById(id);
	}

}
