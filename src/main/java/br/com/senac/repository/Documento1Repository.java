package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.model.Documento1;

@Repository
public interface Documento1Repository extends JpaRepository<Documento1, Long> {

}
