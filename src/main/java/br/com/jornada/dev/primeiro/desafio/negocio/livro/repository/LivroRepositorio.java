package br.com.jornada.dev.primeiro.desafio.negocio.livro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jornada.dev.primeiro.desafio.negocio.livro.LivroEntidade;

/**
 * 
 * @author rafael.altagnam
 *
 */
@Repository
public interface LivroRepositorio extends JpaRepository<LivroEntidade, Long>{


	
}
