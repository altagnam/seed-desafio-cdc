package br.com.jornada.dev.primeiro.desafio.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.model.AutorCadastrado;
import br.com.jornada.dev.primeiro.desafio.model.AutorCadastro;
import br.com.jornada.dev.primeiro.desafio.repository.AutorRepositorio;
import jakarta.validation.Valid;

@Service
public class AutorService {
	
	private final AutorRepositorio repositorio;
	
	public AutorService(final AutorRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	
	/**
	 * Registra um novo autor na base de dados
	 * @param novoAutor
	 * @return
	 */
	public AutorCadastrado cadastrar(final @Valid AutorCadastro novoAutor) {
		return this.repositorio
				.save(novoAutor.toEntidade())
				.toAutorCadastrado();
	}	

}
