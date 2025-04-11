package br.com.jornada.dev.primeiro.desafio.negocio.autor.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.negocio.autor.model.AutorRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.autor.model.AutorResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.autor.repository.AutorRepositorio;
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
	public AutorResponse cadastrar(final @Valid AutorRequest novoAutor) {
		var autorRegistrado = this.repositorio.save(novoAutor.toEntidade());		
		return new AutorResponse(
				autorRegistrado.getId(), 
				autorRegistrado.getNome()
		);
	}	

}
