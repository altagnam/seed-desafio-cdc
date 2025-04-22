package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraResponse.SituacaoCompra;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.repository.CompraRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@Service
public class PedidoCompraService {
	
	// 1 UCP CompraRepositorio
	private final CompraRepositorio compraRepositorio;
	
	// 1 UCP ConstrutorPaisComEstado
	private final ConstrutorPaisComEstado construtorPaisComEstado;
	
	// 1 UCP GeradorPedido
	private final GeradorPedido geradorPedido;
	

	/**
	 * @param estadoRepositorio
	 * @param paisRepositorio
	 * @param livroRepositorio
	 */
	public PedidoCompraService(GeradorPedido geradorPedido, ConstrutorPaisComEstado construtorPaisComEstado, 
			CompraRepositorio compraRepositorio) {
		super();
		this.geradorPedido = geradorPedido;
		this.construtorPaisComEstado = construtorPaisComEstado;
		this.compraRepositorio = compraRepositorio;
	}


	/**
	 * 
	 * @param solicitacao
	 * @return
	 */
	// 1 UCP PedidoCompraResponse
	// 1 UCP PedidoCompraRequest
	// 1 UCP PedidoCompraEntidade
	@Transactional
	public PedidoCompraResponse cadastrar(@NotNull final PedidoCompraRequest solicitacao) {
		var pedido = this.compraRepositorio.save(
			solicitacao.toEntidade(
				geradorPedido,
				construtorPaisComEstado
			)
		);
		
		return new PedidoCompraResponse(pedido.getId(), SituacaoCompra.INICIADA);
	}
	

}
