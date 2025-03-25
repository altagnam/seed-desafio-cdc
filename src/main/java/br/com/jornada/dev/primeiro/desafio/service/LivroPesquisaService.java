package br.com.jornada.dev.primeiro.desafio.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.model.LivroResponse;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;

@Service
public class LivroPesquisaService {
	
	private final LivroRepositorio repositorio;

	
	/**
	 * @param repositorio
	 */
	public LivroPesquisaService(LivroRepositorio repositorio) {
		super();
		this.repositorio = repositorio;
	}


	/**
	 * Retorna uma lista de livros
	 * @return
	 */
	public List<LivroResponse> todos() {
		var todos = this.repositorio.findAll();
		return todos.stream().map(item -> new LivroResponse(item.getId(), item.getTitulo())).toList();
	}


	/**
	 * Retorna uma lista de livros paginada
	 * @param numeroPagina
	 * @param quantidade
	 * @return
	 */
	public Page<LivroResponse> listar(final int numeroPagina, final int quantidade) {
		var pagina = this.repositorio.findAll(PageRequest.of(numeroPagina, quantidade));
		var content = pagina.getContent().stream().map(item -> new LivroResponse(item.getId(), item.getTitulo())).toList();
		return new PageImpl<LivroResponse>(content, pagina.getPageable(), pagina.getTotalElements());
	}

}
