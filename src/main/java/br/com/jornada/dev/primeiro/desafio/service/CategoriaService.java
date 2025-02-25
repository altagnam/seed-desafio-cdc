package br.com.jornada.dev.primeiro.desafio.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.model.CategoriaRequest;
import br.com.jornada.dev.primeiro.desafio.model.CategoriaResponse;
import br.com.jornada.dev.primeiro.desafio.repository.CategoriaRepositorio;
import jakarta.validation.Valid;

@Service
public class CategoriaService {
	
	private final CategoriaRepositorio repositorio;
	
	public CategoriaService(final CategoriaRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	
	/**
	 * Registra uma nova categoria.
	 * @param novaCategoria
	 * @return
	 */
	public CategoriaResponse cadastrar(final @Valid CategoriaRequest novaCategoria) {
		return this.repositorio
				.save(novaCategoria.toEntidade())
				.toCategoriaResponse();
	}	

}
