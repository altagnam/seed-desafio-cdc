package br.com.jornada.dev.primeiro.desafio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.model.PedidoCompraRequest;
import br.com.jornada.dev.primeiro.desafio.model.PedidoCompraResponse;
import br.com.jornada.dev.primeiro.desafio.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.PaisRepositorio;
import br.com.jornada.dev.primeiro.desafio.service.PedidoCompraService;
import br.com.jornada.dev.primeiro.desafio.validador.CompraValidator;
import br.com.jornada.dev.primeiro.desafio.validador.EstadoPertencePaisValidator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido-compra")
public class PedidoCompraController {
	
	private final PedidoCompraService service;
	private final PaisRepositorio paisRepositorio;
	private final EstadoRepositorio estadoRepositorio;
	private final LivroRepositorio livroRepositorio;
	
	
	/**
	 * @param service
	 * @param paisRepositorio
	 * @param estadoRepositorio
	 */
	public PedidoCompraController(PedidoCompraService service, PaisRepositorio paisRepositorio,
			EstadoRepositorio estadoRepositorio, LivroRepositorio livroRepositorio) {
		super();
		this.service = service;
		this.paisRepositorio = paisRepositorio;
		this.estadoRepositorio = estadoRepositorio;
		this.livroRepositorio = livroRepositorio;
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(
				new EstadoPertencePaisValidator(paisRepositorio, estadoRepositorio),
				new CompraValidator(livroRepositorio)
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
	public PedidoCompraResponse cadastrar(@Valid @RequestBody final PedidoCompraRequest solicitacao) {
		return this.service.cadastrar(solicitacao);
	}

}
