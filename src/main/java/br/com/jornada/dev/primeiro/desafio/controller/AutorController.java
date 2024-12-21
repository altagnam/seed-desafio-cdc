package br.com.jornada.dev.primeiro.desafio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.model.Autor;
import br.com.jornada.dev.primeiro.desafio.model.AutorCadastrado;
import br.com.jornada.dev.primeiro.desafio.service.AutorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {
	
	private final AutorService service;
	public AutorController(final AutorService service) {
		this.service = service;
	}
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public AutorCadastrado cadastrar(@RequestBody @Valid final Autor novoAutor) {
		return this.service.cadastrar(novoAutor);
	}

}
