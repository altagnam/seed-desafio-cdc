package br.com.jornada.dev.primeiro.desafio.negocio.pais.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.negocio.pais.model.PaisRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.model.PaisResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.service.PaisService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/paises")
public class PaisController {
	
	private final PaisService paisService;
	public PaisController(final PaisService paisService) {
		this.paisService = paisService;
	}
	
	
	/**
	 * Permite receber uma requisição e enviar o comando para 
	 * registrar um novo pais na base de dados.
	 * 
	 * @param solicitacao
	 * @return
	 */
	@PostMapping
	public PaisResponse cadastrar(@Valid @RequestBody PaisRequest solicitacao) {
		return this.paisService.cadastrar(solicitacao);
	}

}
