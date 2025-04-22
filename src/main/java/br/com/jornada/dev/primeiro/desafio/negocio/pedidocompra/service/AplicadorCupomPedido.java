package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.negocio.cupom.CupomEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.cupom.repository.CupomRepository;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoCompraEntidade;
import jakarta.persistence.NoResultException;

/**
 * 
 * @author rafael.altagnam
 *
 */
@Component
public class AplicadorCupomPedido {

	// 1 UCP CupomRepository
	private CupomRepository cupomRepository;

	public AplicadorCupomPedido(final CupomRepository cupomRepository) {
		this.cupomRepository = cupomRepository;

	}

	// 1 UCP PedidoCompraEntidade
	public void aplicar(final PedidoCompraEntidade pedido, String cupom) {
		// 1 UCP condicional
		if (Strings.isNotBlank(cupom)) {

			// 1 UCP CupomEntidade
			CupomEntidade cupomEncontrado = cupomRepository.findByCodigo(cupom).orElseThrow(() -> new NoResultException("Cupom não encontrado."));
			Assert.isTrue(cupomEncontrado.isValido(), "Cupom inválido");
			pedido.aplicarCupom(cupomEncontrado);
		}
	}

}
