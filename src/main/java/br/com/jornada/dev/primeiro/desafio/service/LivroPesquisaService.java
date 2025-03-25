package br.com.jornada.dev.primeiro.desafio.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.jornada.dev.primeiro.desafio.model.LivroDadosSimplesResponse;
import br.com.jornada.dev.primeiro.desafio.model.LivroDetalheResponse;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;
import jakarta.persistence.NoResultException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Service
public class LivroPesquisaService {
	
	// 1 ponto para LivroRepositorio
	private final LivroRepositorio repositorio;

	
	/**
	 * @param repositorio
	 */
	public LivroPesquisaService(LivroRepositorio repositorio) {
		super();
		this.repositorio = repositorio;
	}

	/**
	 * Busca um livro mediante ao ID
	 * @param id
	 * @return
	 */
	// 1 ponto para LivroDetalheResponse
	public LivroDetalheResponse buscarPorId(@NotNull @Min(0) final Long id) {
		// 1 ponto para livro
		// 1 ponto para funcao como argumento
		var livro = this.repositorio.findById(id).orElseThrow(NoResultException::new);
		
		// 1 ponto para LivroDetalheResponse
		return new LivroDetalheResponse(livro);
	}
	

	/**
	 * Retorna uma lista de livros
	 * @return
	 */
	// 1 ponto para LivroDadosSimplesResponse
	public List<LivroDadosSimplesResponse> todos() {
		return this.repositorio.findAll()
				.stream()
				.map(item -> new LivroDadosSimplesResponse(item.getId(), item.getTitulo())) // 1 ponto para funcao como argumento
				.toList(); 
		
	}


	/**
	 * Retorna uma lista de livros paginada
	 * @param numeroPagina
	 * @param quantidade
	 * @return
	 */
	public Page<LivroDadosSimplesResponse> listar(final int numeroPagina, final int quantidade) {
		var pagina = this.repositorio.findAll(PageRequest.of(numeroPagina, quantidade));
		var conteudo = pagina.getContent()
				.stream()
				.map(item -> new LivroDadosSimplesResponse(item.getId(), item.getTitulo())) // 1 ponto para funcao como argumento
				.toList(); 
		
		return new PageImpl<LivroDadosSimplesResponse>(
				conteudo, 
				pagina.getPageable(), 
				pagina.getTotalElements()
		);
	}

}
