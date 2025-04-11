package br.com.jornada.dev.primeiro.desafio.negocio.livro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.negocio.livro.model.NovoLivroResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.model.LivroDetalheResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.service.LivroPesquisaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Controlador responsável por permitir a pesquisa de livros
 * @author rafael.altagnam
 *
 */
@RestController
@RequestMapping("/livros/pesquisa")
public class LivroPesquisaController {
	
	// 1 UCP
	private final LivroPesquisaService service;
	public LivroPesquisaController(final LivroPesquisaService service) {
		this.service = service;
	}
	
	/**
	 * Retorna todos os livros
	 * @return
	 */
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	// 2 UCP
	public LivroDetalheResponse buscarPorId(@Valid @NotNull @Min(1) @PathVariable(value = "id", required = true) final Long id) {
		return service.buscarPorId(id);
	}
	
	
	/**
	 * Retorna todos os livros
	 * @return
	 */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	// 3 UCP
	public List<NovoLivroResponse> todos() {
		return service.todos();
	}
	
	
	/***
	 * Retorna uma lista paginada de livros
	 * @param pagina
	 * @param total
	 * @return
	 */
	@GetMapping("/paginada")
	@ResponseStatus(code = HttpStatus.OK)
	public Page<NovoLivroResponse> listaPaginada(@RequestParam(name = "pagina", required = true) final int pagina, 
			@RequestParam(name = "total", required = false, defaultValue = "10") final Optional<Integer> total) {
		return service.listar(pagina, total.orElse(10));
	}
	
}
