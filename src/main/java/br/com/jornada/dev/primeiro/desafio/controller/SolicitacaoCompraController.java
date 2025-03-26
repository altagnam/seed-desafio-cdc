package br.com.jornada.dev.primeiro.desafio.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.model.SolicitacaoCompraRequest;
import br.com.jornada.dev.primeiro.desafio.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.PaisRepositorio;
import br.com.jornada.dev.primeiro.desafio.service.SolicitacaoCompraService;
import br.com.jornada.dev.primeiro.desafio.validador.EstadoPertencePaisValidator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/solicitacao-compra")
public class SolicitacaoCompraController {
	
	private final SolicitacaoCompraService service;
	private final PaisRepositorio paisRepositorio;
	private final EstadoRepositorio estadoRepositorio;
	
	
	/**
	 * @param service
	 * @param paisRepositorio
	 * @param estadoRepositorio
	 */
	public SolicitacaoCompraController(SolicitacaoCompraService service, PaisRepositorio paisRepositorio,
			EstadoRepositorio estadoRepositorio) {
		super();
		this.service = service;
		this.paisRepositorio = paisRepositorio;
		this.estadoRepositorio = estadoRepositorio;
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new EstadoPertencePaisValidator(paisRepositorio, estadoRepositorio));
	}


	/**
	 * Permite receber uma requisição e enviar o comando para 
	 * registrar um novo pais na base de dados.
	 * 
	 * @param solicitacao
	 * @return
	 */
	@PostMapping
	public String cadastrar(@Valid @RequestBody SolicitacaoCompraRequest solicitacao) {
		return this.service.cadastrar(solicitacao);
	}

}
