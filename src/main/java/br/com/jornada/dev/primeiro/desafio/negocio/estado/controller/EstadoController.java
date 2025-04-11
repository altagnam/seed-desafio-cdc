package br.com.jornada.dev.primeiro.desafio.negocio.estado.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.negocio.estado.model.EstadoRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.estado.model.EstadoResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.estado.service.EstadoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	private final EstadoService service;
	public EstadoController(final EstadoService paisService) {
		this.service = paisService;
	}
	
	
	/**
	 * Permite receber uma requisição e enviar o comando para 
	 * registrar um novo Estado na base de dados.
	 * 
	 * @param solicitacao
	 * @return
	 */
	@PostMapping
	public EstadoResponse cadastrar(@Valid @RequestBody EstadoRequest solicitacao) {
		return this.service.cadastrar(solicitacao);
	}

}
