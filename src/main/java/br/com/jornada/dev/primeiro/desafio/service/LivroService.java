package br.com.jornada.dev.primeiro.desafio.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.model.LivroRequest;
import br.com.jornada.dev.primeiro.desafio.model.LivroResponse;
import br.com.jornada.dev.primeiro.desafio.repository.AutorRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.CategoriaRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;

@Service
public class LivroService {
	
	private final LivroRepositorio repositorio;
	private final CategoriaRepositorio categoriaRepositorio;
	private final AutorRepositorio autorRepositorio;

	
	/**
	 * @param repositorio
	 * @param categoriaRepositorio
	 * @param autorRepositorio
	 */
	public LivroService(LivroRepositorio repositorio, CategoriaRepositorio categoriaRepositorio,
			AutorRepositorio autorRepositorio) {
		super();
		this.repositorio = repositorio;
		this.categoriaRepositorio = categoriaRepositorio;
		this.autorRepositorio = autorRepositorio;
	}




	/**
	 * Registra um novo novo livro
	 * @param novoLivro
	 * @return
	 */
	public LivroResponse cadastrar(final @Valid @Null LivroRequest novoLivro) {
		return this.repositorio
				.save(novoLivro.toEntidade(categoriaRepositorio, autorRepositorio))
				.toLivroResponse();
	}

}
