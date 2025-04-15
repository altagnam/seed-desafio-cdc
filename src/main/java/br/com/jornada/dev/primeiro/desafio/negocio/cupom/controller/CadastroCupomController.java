package br.com.jornada.dev.primeiro.desafio.negocio.cupom.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.negocio.cupom.model.NovoCupomRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.cupom.model.NovoCupomResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.cupom.repository.CupomRepository;
import jakarta.validation.Valid;

/**
 * A cdc pode liberar cupons de desconto com valores e validade variados. 
 * Precisamos ter suporte a isso.
 * 
 * @author rafael.altagnam
 *
 */
@RestController
@RequestMapping("/cupons")
public class CadastroCupomController {
	
	private CupomRepository repository;
	public CadastroCupomController(final CupomRepository repository) {
		this.repository = repository;
	
	}
	
	
	/**
	 * Funcionalidade que permite o cadastro de um novo cupom de desconto
	 * @param request
	 * @return
	 */
	@PostMapping
	public NovoCupomResponse cadastrar (@Valid @RequestBody final NovoCupomRequest request) {
		var cupomCadastrado = repository.save(request.toEntidade());
		return new NovoCupomResponse(cupomCadastrado);
	}

}
