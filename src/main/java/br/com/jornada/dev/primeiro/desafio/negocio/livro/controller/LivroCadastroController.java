package br.com.jornada.dev.primeiro.desafio.negocio.livro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.negocio.livro.model.NovoLivroResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.model.NovoLivroRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.service.LivroService;
import jakarta.validation.Valid;

/**
 * Controlador responsável por receber os dados vinculados a solicitação de cadastro de um livro
 * @author rafael.altagnam
 *
 */
@RestController
@RequestMapping("/livros")
public class LivroCadastroController {
	
	private final LivroService service;
	public LivroCadastroController(final LivroService service) {
		this.service = service;
	}
	
	/**
	 * Cadastra um  {@link NovoLivroRequest}
	 * @param novoLivro
	 * @return
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public NovoLivroResponse cadastrar(@Valid @RequestBody final NovoLivroRequest novoLivro) {
		return service.cadastrar(novoLivro);
	}
	
}
