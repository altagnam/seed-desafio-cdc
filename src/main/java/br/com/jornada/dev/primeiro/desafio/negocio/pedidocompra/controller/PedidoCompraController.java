package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.negocio.cupom.repository.CupomRepository;
import br.com.jornada.dev.primeiro.desafio.negocio.estado.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.repository.LivroRepository;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.repository.PaisRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.service.PedidoCompraService;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.validator.PedidoCompraCupomValidator;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.validator.PedidoCompraEstadoPertencePaisValidator;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.validator.PedidoCompraNovoItemValidator;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.validator.PedidoCompraValorTotalValidator;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido-compra")
public class PedidoCompraController {
	
	private final PedidoCompraService service;
	private final PaisRepositorio paisRepositorio;
	private final EstadoRepositorio estadoRepositorio;
	private final LivroRepository livroRepositorio;
	private final CupomRepository cupomRepository;
	
	
	/**
	 * @param service
	 * @param paisRepositorio
	 * @param estadoRepositorio
	 */
	public PedidoCompraController(PedidoCompraService service, PaisRepositorio paisRepositorio,
			EstadoRepositorio estadoRepositorio, LivroRepository livroRepositorio, CupomRepository cupomRepository) {
		super();
		this.service = service;
		this.paisRepositorio = paisRepositorio;
		this.estadoRepositorio = estadoRepositorio;
		this.livroRepositorio = livroRepositorio;
		this.cupomRepository = cupomRepository;
	}


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		var pedidoValidator = new PedidoCompraValorTotalValidator(livroRepositorio);
		binder.addValidators(
				new PedidoCompraCupomValidator(cupomRepository),
				new PedidoCompraEstadoPertencePaisValidator(paisRepositorio, estadoRepositorio),
				new PedidoCompraNovoItemValidator(pedidoValidator)
				
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
