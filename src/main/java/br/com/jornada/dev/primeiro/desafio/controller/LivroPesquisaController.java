package br.com.jornada.dev.primeiro.desafio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.model.LivroResponse;
import br.com.jornada.dev.primeiro.desafio.service.LivroPesquisaService;
import jakarta.validation.constraints.Min;

/**
 * Controlador responsável por receber os dados vinculados a solicitação de cadastro de um livro
 * @author rafael.altagnam
 *
 */
@RestController
@RequestMapping("/livros/pesquisa")
public class LivroPesquisaController {
	
	private final LivroPesquisaService service;
	public LivroPesquisaController(final LivroPesquisaService service) {
		this.service = service;
	}
	
	
	/**
	 * Retorna todos os livros
	 * @return
	 */
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<LivroResponse> todos() {
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
	public Page<LivroResponse> listaPaginada(@RequestParam(name = "pagina", required = true) @Min(1) final int pagina, 
			@RequestParam(name = "total", required = false, defaultValue = "10") final Optional<Integer> total) {
		return service.listar(pagina, total.orElse(10));
	}
	
}
