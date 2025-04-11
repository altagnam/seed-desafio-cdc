package br.com.jornada.dev.primeiro.desafio.negocio.livro.service;

import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.negocio.livro.model.NovoLivroResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.autor.repository.AutorRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.categoria.repository.CategoriaRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.model.NovoLivroRequest;
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
	public NovoLivroResponse cadastrar(final @Valid @Null NovoLivroRequest novoLivro) {
		var livroRegistrado = this.repositorio.save(novoLivro.toEntidade(categoriaRepositorio, autorRepositorio));
		return new NovoLivroResponse(
				livroRegistrado.getId(), 
				livroRegistrado.getTitulo()
		);
	}

}
