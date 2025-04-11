package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoCompraEntidade;

/**
 * 
 * @author rafael.altagnam
 *
 */
@Repository
public interface CompraRepositorio extends JpaRepository<PedidoCompraEntidade, Long>{


	
}
