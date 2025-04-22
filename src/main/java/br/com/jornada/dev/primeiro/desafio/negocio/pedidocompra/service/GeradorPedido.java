package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.service;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.jornada.dev.primeiro.desafio.negocio.livro.repository.LivroRepository;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoCompraEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.service.ConstrutorPaisComEstado.PaisComPossivelEstado;

/**
 * 
 * @author rafael.altagnam
 *
 */
@Component
public class GeradorPedido {

	//1 UCP LivroRepository
	private final LivroRepository livroRepositorio;
	
	//1 UCP AplicadorCupomPedido
	private final AplicadorCupomPedido aplicadorCupomPedido;	
	

	/**
	 * 
	 * @param livroRepositorio
	 * @param aplicadorCupomPedido
	 */
	public GeradorPedido(final LivroRepository livroRepositorio, final AplicadorCupomPedido aplicadorCupomPedido) {
		this.livroRepositorio = livroRepositorio;
		this.aplicadorCupomPedido = aplicadorCupomPedido;
	}
	
	
	//1 UCP PedidoCompraRequest
	//1 UCP PaisComPossivelEstado
	public PedidoCompraEntidade gerar (final PedidoCompraRequest request, final PaisComPossivelEstado paisEstado) {
		//1 UCP PedidoCompraEntidade
		//1 UCP PedidoEntidade
		Function<PedidoCompraEntidade, PedidoEntidade> funcaoCriacaoPedido = request.getPedido().toEntidade(livroRepositorio);
		
		PedidoCompraEntidade pedidoCompra = new PedidoCompraEntidade(
				request.getEmail(), 
				request.getNome(), 
				request.getSobreNome(), 
				request.getDocumento(), 
				request.getEndereco(), 
				request.getComplemento(), 
				request.getCidade(), 
				request.getTelefone(), 
				request.getCep(), 
				paisEstado.possivelEstado(), 
				paisEstado.pais(), 
				funcaoCriacaoPedido
		);

		aplicadorCupomPedido.aplicar(pedidoCompra, request.getCupom());
		return pedidoCompra;
	}
}
