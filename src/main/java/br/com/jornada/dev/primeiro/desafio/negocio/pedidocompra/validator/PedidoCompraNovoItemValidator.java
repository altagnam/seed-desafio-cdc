package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoCompraRequest;

@Component
public class PedidoCompraNovoItemValidator implements Validator {
	
	private final PedidoCompraVerificarPedidoValidator pedidoValidator;
	
	public PedidoCompraNovoItemValidator(final PedidoCompraVerificarPedidoValidator pedidoValidator) {
		this.pedidoValidator = pedidoValidator;
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
		
		var compra = (PedidoCompraRequest) target;		
		errors.setNestedPath("pedido");
		ValidationUtils.invokeValidator(this.pedidoValidator, compra.getPedido(), errors);
		
	}

}
