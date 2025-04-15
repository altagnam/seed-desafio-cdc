package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.negocio.cupom.repository.CupomRepository;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraRequest;
import jakarta.persistence.NoResultException;

@Component
public class PedidoCompraCupomValidator implements Validator {
	
	private final CupomRepository cupomRepositorio;
	public PedidoCompraCupomValidator(final CupomRepository cupomRepositorio) {
		this.cupomRepositorio = cupomRepositorio;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(PedidoCompraRequest.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		var pedido = (PedidoCompraRequest) target;
		if (!pedido.isCupomInformado()) {
			return;
		}
		
		var cupom = cupomRepositorio.findByCodigo(pedido.getCupom()).orElseThrow(() -> new NoResultException("Cupom não encotrado"));
		Assert.isTrue(cupom.isValido(), "Cupom inválido");
	}

}
