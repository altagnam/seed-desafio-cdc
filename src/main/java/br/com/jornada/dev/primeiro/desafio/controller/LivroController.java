package br.com.jornada.dev.primeiro.desafio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.model.LivroRequest;
import br.com.jornada.dev.primeiro.desafio.model.LivroDadosSimplesResponse;
import br.com.jornada.dev.primeiro.desafio.service.LivroService;
import jakarta.validation.Valid;

/**
 * Controlador responsável por receber os dados vinculados a solicitação de cadastro de um livro
 * @author rafael.altagnam
 *
 */
@RestController
@RequestMapping("/livros")
public class LivroController {
	
	private final LivroService service;
	public LivroController(final LivroService service) {
		this.service = service;
	}
	
	/**
	 * Cadastra um  {@link LivroRequest}
	 * @param novoLivro
	 * @return
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public LivroDadosSimplesResponse cadastrar(@Valid @RequestBody final LivroRequest novoLivro) {
		return service.cadastrar(novoLivro);
	}
	
}
