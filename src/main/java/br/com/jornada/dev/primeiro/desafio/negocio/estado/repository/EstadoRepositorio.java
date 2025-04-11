package br.com.jornada.dev.primeiro.desafio.negocio.estado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jornada.dev.primeiro.desafio.negocio.estado.EstadoEntidade;

/**
 * 
 * @author rafael.altagnam
 *
 */
@Repository
public interface EstadoRepositorio extends JpaRepository<EstadoEntidade, Long>{

	@Query("SELECT COUNT(ee.id) FROM EstadoEntidade ee WHERE ee.pais.id = :idPais")
	Integer countByPais(final Long idPais);
	
}
