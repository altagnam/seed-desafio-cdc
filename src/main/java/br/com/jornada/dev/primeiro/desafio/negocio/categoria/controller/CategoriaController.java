package br.com.jornada.dev.primeiro.desafio.negocio.categoria.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.negocio.categoria.model.CategoriaRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.categoria.model.CategoriaResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.categoria.service.CategoriaService;
import jakarta.validation.Valid;

/**
 * Controlador responsável por receber os dados vinculados a uma categoria e realizar o seu cadastro na base de dados.
 * @author rafael.altagnam
 *
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	private final CategoriaService service;
	public CategoriaController(final CategoriaService service) {
		this.service = service;
	}
	
	/**
	 * Cadastra uma nova {@link CategoriaRequest}
	 * @param autor
	 * @return
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public CategoriaResponse cadastrar(@Valid @RequestBody final CategoriaRequest autor) {
		return this.service.cadastrar(autor);
	}


}
