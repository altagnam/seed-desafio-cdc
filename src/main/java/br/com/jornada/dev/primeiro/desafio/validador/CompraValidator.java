package br.com.jornada.dev.primeiro.desafio.validador;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.model.CompraRequest;

@Component
public class CompraValidator implements Validator {
	
	private final PedidoValidator pedidoValidator;
	
	public CompraValidator(final PedidoValidator pedidoValidator) {
		this.pedidoValidator = pedidoValidator;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(CompraRequest.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		var compra = (CompraRequest) target;		
		errors.setNestedPath("pedido");
		ValidationUtils.invokeValidator(this.pedidoValidator, compra.getPedido(), errors);
		
	}

}
