package br.com.jornada.dev.primeiro.desafio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.model.CompraRequest;
import br.com.jornada.dev.primeiro.desafio.model.CompraResponse;
import br.com.jornada.dev.primeiro.desafio.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.PaisRepositorio;
import br.com.jornada.dev.primeiro.desafio.service.CompraService;
import br.com.jornada.dev.primeiro.desafio.validador.CompraValidator;
import br.com.jornada.dev.primeiro.desafio.validador.EstadoPertencePaisValidator;
import br.com.jornada.dev.primeiro.desafio.validador.PedidoValidator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido-compra")
public class PedidoCompraController {
	
	private final CompraService service;
	private final PaisRepositorio paisRepositorio;
	private final EstadoRepositorio estadoRepositorio;
	private final LivroRepositorio livroRepositorio;
	
	
	/**
	 * @param service
	 * @param paisRepositorio
	 * @param estadoRepositorio
	 */
	public PedidoCompraController(CompraService service, PaisRepositorio paisRepositorio,
			EstadoRepositorio estadoRepositorio, LivroRepositorio livroRepositorio) {
		super();
		this.service = service;
		this.paisRepositorio = paisRepositorio;
		this.estadoRepositorio = estadoRepositorio;
		this.livroRepositorio = livroRepositorio;
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		var pedidoValidator = new PedidoValidator(livroRepositorio);
		binder.addValidators(
				new EstadoPertencePaisValidator(paisRepositorio, estadoRepositorio),
				new CompraValidator(pedidoValidator)
				
		);
	}


	/**
	 * Permite receber uma requisição e enviar o comando para 
	 * registrar um novo pais na base de dados.
	 * 
	 * @param solicitacao
	 * @return
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CompraResponse cadastrar(@Valid @RequestBody final CompraRequest solicitacao) {
		return this.service.cadastrar(solicitacao);
	}

}
