package br.com.jornada.dev.primeiro.desafio.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.model.CompraRequest;
import br.com.jornada.dev.primeiro.desafio.model.CompraResponse;
import br.com.jornada.dev.primeiro.desafio.model.CompraResponse.SituacaoCompra;
import br.com.jornada.dev.primeiro.desafio.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.PaisRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.CompraRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@Service
public class CompraService {
	
	private final EstadoRepositorio estadoRepositorio;
	private final PaisRepositorio paisRepositorio;
	private final LivroRepositorio livroRepositorio;	
	private final CompraRepositorio compraRepositorio;
	

	/**
	 * @param estadoRepositorio
	 * @param paisRepositorio
	 * @param livroRepositorio
	 */
	public CompraService(EstadoRepositorio estadoRepositorio, PaisRepositorio paisRepositorio,
			LivroRepositorio livroRepositorio, CompraRepositorio compraRepositorio) {
		super();
		this.estadoRepositorio = estadoRepositorio;
		this.paisRepositorio = paisRepositorio;
		this.livroRepositorio = livroRepositorio;
		this.compraRepositorio = compraRepositorio;
	}


	/**
	 * 
	 * @param solicitacao
	 * @return
	 */
	@Transactional
	public CompraResponse cadastrar(@NotNull final CompraRequest solicitacao) {
		var pedido = this.compraRepositorio.save(solicitacao.toEntidade(
				paisRepositorio, 
				estadoRepositorio, 
				livroRepositorio)
		);
		
		return new CompraResponse(pedido.getId(), SituacaoCompra.INICIADA);
	}
	

}
