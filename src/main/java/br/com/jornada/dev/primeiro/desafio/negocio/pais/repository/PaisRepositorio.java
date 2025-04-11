package br.com.jornada.dev.primeiro.desafio.negocio.pais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jornada.dev.primeiro.desafio.negocio.pais.PaisEntidade;

/**
 * 
 * @author rafael.altagnam
 *
 */
@Repository
public interface PaisRepositorio extends JpaRepository<PaisEntidade, Long>{


	
}
