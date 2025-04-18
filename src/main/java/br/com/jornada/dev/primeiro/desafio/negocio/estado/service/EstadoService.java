package br.com.jornada.dev.primeiro.desafio.negocio.estado.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.negocio.estado.model.EstadoRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.estado.model.EstadoResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.estado.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.repository.PaisRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class EstadoService {

	private final EstadoRepositorio repository;
	private final PaisRepositorio paisRepositorio;
	
	/**
	 * @param repository
	 */
	public EstadoService(final EstadoRepositorio repository, final PaisRepositorio paisRepositorio) {
		super();
		this.repository = repository;
		this.paisRepositorio = paisRepositorio;
	}

	/**
	 * Permite cadastrar um novo estado/ federaçao vinculado a um país
	 * @param solicitacao
	 * @return
	 */
	public EstadoResponse cadastrar(@Valid @NotNull EstadoRequest solicitacao) {
		var estado = this.repository.save(solicitacao.toModel(paisRepositorio));
		return new EstadoResponse(estado);
	}

}
