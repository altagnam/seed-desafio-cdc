package br.com.jornada.dev.primeiro.desafio.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.model.PedidoCompraRequest;
import br.com.jornada.dev.primeiro.desafio.model.PedidoCompraResponse;
import br.com.jornada.dev.primeiro.desafio.model.PedidoCompraResponse.SituacaoCompra;
import br.com.jornada.dev.primeiro.desafio.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.PaisRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.PedidoCompraRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@Service
public class PedidoCompraService {
	
	private final EstadoRepositorio estadoRepositorio;
	private final PaisRepositorio paisRepositorio;
	private final LivroRepositorio livroRepositorio;	
	private final PedidoCompraRepositorio pedidoCompraRepositorio;
	

	/**
	 * @param estadoRepositorio
	 * @param paisRepositorio
	 * @param livroRepositorio
	 */
	public PedidoCompraService(EstadoRepositorio estadoRepositorio, PaisRepositorio paisRepositorio,
			LivroRepositorio livroRepositorio, PedidoCompraRepositorio pedidoCompraRepositorio) {
		super();
		this.estadoRepositorio = estadoRepositorio;
		this.paisRepositorio = paisRepositorio;
		this.livroRepositorio = livroRepositorio;
		this.pedidoCompraRepositorio = pedidoCompraRepositorio;
	}


	/**
	 * 
	 * @param solicitacao
	 * @return
	 */
	@Transactional
	public PedidoCompraResponse cadastrar(@NotNull final PedidoCompraRequest solicitacao) {
		var pedido = this.pedidoCompraRepositorio.save(solicitacao.toEntidade(
				paisRepositorio, 
				estadoRepositorio, 
				livroRepositorio)
		);
		
		return new PedidoCompraResponse(pedido.getId(), SituacaoCompra.INICIADA);
	}
	

}
