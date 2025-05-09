package br.com.jornada.dev.primeiro.desafio.negocio.categoria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jornada.dev.primeiro.desafio.negocio.categoria.CategoriaEntidade;

/**
 * 
 * @author rafael.altagnam
 *
 */
@Repository
public interface CategoriaRepositorio extends JpaRepository<CategoriaEntidade, Long>{

	Optional<CategoriaEntidade> findByNome(String nome);

	
}
