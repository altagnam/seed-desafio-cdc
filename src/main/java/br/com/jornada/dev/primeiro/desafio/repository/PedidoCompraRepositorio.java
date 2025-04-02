package br.com.jornada.dev.primeiro.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jornada.dev.primeiro.desafio.entidade.PedidoCompraEntidade;

/**
 * 
 * @author rafael.altagnam
 *
 */
@Repository
public interface PedidoCompraRepositorio extends JpaRepository<PedidoCompraEntidade, Long>{


	
}
