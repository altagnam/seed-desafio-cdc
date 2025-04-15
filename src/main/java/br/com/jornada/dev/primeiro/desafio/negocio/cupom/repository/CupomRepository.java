package br.com.jornada.dev.primeiro.desafio.negocio.cupom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jornada.dev.primeiro.desafio.negocio.cupom.CupomEntidade;

@Repository
public interface CupomRepository extends JpaRepository<CupomEntidade, Long>{

	
	/**
	 * Busca uma cupom de acordo com o código
	 * @param codigo
	 * @return
	 */
	Optional<CupomEntidade> findByCodigo(final String codigo);
}
