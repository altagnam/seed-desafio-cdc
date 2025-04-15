package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.negocio.cupom.repository.CupomRepository;
import br.com.jornada.dev.primeiro.desafio.negocio.estado.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.repository.LivroRepository;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.repository.PaisRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraResponse.SituacaoCompra;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.repository.CompraRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@Service
public class PedidoCompraService {
	
	private final EstadoRepositorio estadoRepositorio;
	private final PaisRepositorio paisRepositorio;
	private final LivroRepository livroRepositorio;	
	private final CompraRepositorio compraRepositorio;
	private final CupomRepository cupomRepostitory;
	

	/**
	 * @param estadoRepositorio
	 * @param paisRepositorio
	 * @param livroRepositorio
	 */
	public PedidoCompraService(EstadoRepositorio estadoRepositorio, PaisRepositorio paisRepositorio,
			LivroRepository livroRepositorio, CompraRepositorio compraRepositorio, CupomRepository cupomRepostitory) {
		super();
		this.estadoRepositorio = estadoRepositorio;
		this.paisRepositorio = paisRepositorio;
		this.livroRepositorio = livroRepositorio;
		this.compraRepositorio = compraRepositorio;
		this.cupomRepostitory = cupomRepostitory;
	}


	/**
	 * 
	 * @param solicitacao
	 * @return
	 */
	@Transactional
	public PedidoCompraResponse cadastrar(@NotNull final PedidoCompraRequest solicitacao) {
		var pedido = this.compraRepositorio.save(solicitacao.toEntidade(
				cupomRepostitory,
				paisRepositorio, 
				estadoRepositorio, 
				livroRepositorio
			)
		);
		
		return new PedidoCompraResponse(pedido.getId(), SituacaoCompra.INICIADA);
	}
	

}
