package br.com.jornada.dev.primeiro.desafio.negocio.pais.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.negocio.pais.model.PaisRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.model.PaisResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.repository.PaisRepositorio;
import jakarta.validation.constraints.NotNull;

@Service
public class PaisService {
	
	private final PaisRepositorio repositorio;
	public PaisService(final PaisRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	/**
	 * Permite inserir um novo pais na base de dados
	 * @param solicitacao
	 * @return
	 */
	public PaisResponse cadastrar(@NotNull final PaisRequest solicitacao) {
		var pais = this.repositorio.save(solicitacao.toEntidade());
		return new PaisResponse(
				pais.getId(), 
				pais.getNome(
		));
	}

}
