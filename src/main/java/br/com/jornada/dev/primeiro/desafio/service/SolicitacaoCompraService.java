package br.com.jornada.dev.primeiro.desafio.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.model.SolicitacaoCompraRequest;
import br.com.jornada.dev.primeiro.desafio.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.PaisRepositorio;
import jakarta.validation.constraints.NotNull;

@Service
public class SolicitacaoCompraService {
	
	private final EstadoRepositorio estadoRepositorio;
	private final PaisRepositorio paisRepositorio;
	
	/**
	 * @param estadoRepositorio
	 * @param paisRepositorio
	 */
	public SolicitacaoCompraService(EstadoRepositorio estadoRepositorio, PaisRepositorio paisRepositorio) {
		super();
		this.estadoRepositorio = estadoRepositorio;
		this.paisRepositorio = paisRepositorio;
	}
	
	
	public String cadastrar(@NotNull final SolicitacaoCompraRequest solicotacao) {
		return solicotacao.toEntidade(paisRepositorio, estadoRepositorio).toString();
	}
	

}
