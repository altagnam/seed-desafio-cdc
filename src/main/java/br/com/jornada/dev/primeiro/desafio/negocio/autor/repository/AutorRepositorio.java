package br.com.jornada.dev.primeiro.desafio.negocio.autor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jornada.dev.primeiro.desafio.negocio.autor.AutorEntidade;

/**
 * 
 * @author rafael.altagnam
 *
 */
@Repository
public interface AutorRepositorio extends JpaRepository<AutorEntidade, Long>{

	Optional<AutorEntidade> findByEmail(String email);

	
}
