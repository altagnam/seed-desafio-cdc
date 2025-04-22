package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.CompraRealizadaResponse;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.repository.CompraRepositorio;

@RestController
@RequestMapping("/compras")
public class CompraRealizadaController {
	
	
	private CompraRepositorio compraRepositorio;
	public CompraRealizadaController(final CompraRepositorio compraRepositorio) {
		this.compraRepositorio = compraRepositorio;
	
	}
	
	@GetMapping("/{id}")
	public CompraRealizadaResponse buscar(@PathVariable(name = "id") Long idCompra) {
		var compra = compraRepositorio.findById(idCompra).orElseThrow(() -> new IllegalArgumentException("Compra não encontrada"));
		return new CompraRealizadaResponse(compra);
	}

}
