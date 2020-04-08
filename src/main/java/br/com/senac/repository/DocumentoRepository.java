package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
