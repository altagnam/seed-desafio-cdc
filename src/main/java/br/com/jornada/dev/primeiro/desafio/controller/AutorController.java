package br.com.jornada.dev.primeiro.desafio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.model.AutorResponse;
import br.com.jornada.dev.primeiro.desafio.model.AutorRequest;
import br.com.jornada.dev.primeiro.desafio.service.AutorService;
import br.com.jornada.dev.primeiro.desafio.validador.AutorValidator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {
	
	private final AutorService service;
	private final AutorValidator autorValidator;
	public AutorController(final AutorService service, final AutorValidator autorValidator) {
		this.service = service;
		this.autorValidator = autorValidator;
	}
	
	@InitBinder
	public void addValidator(WebDataBinder binder) {
		binder.addValidators(autorValidator);
		
	}
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public AutorResponse cadastrar(@Valid @RequestBody final AutorRequest autor) {
		return this.service.cadastrar(autor);
	}

}
