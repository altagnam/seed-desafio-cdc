package br.com.jornada.dev.primeiro.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jornada.dev.primeiro.desafio.entidade.CategoriaEntidade;

/**
 * 
 * @author rafael.altagnam
 *
 */
@Repository
public interface CategoriaRepositorio extends JpaRepository<CategoriaEntidade, Long>{

	Optional<CategoriaEntidade> findByNome(String nome);

	
}
