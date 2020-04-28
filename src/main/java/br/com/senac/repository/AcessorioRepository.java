package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.model.Acessorio;

@Repository
public interface AcessorioRepository extends JpaRepository<Acessorio, Integer> {

}
