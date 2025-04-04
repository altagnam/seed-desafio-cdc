package br.com.jornada.dev.primeiro.desafio.validador;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.model.PedidoRequest;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;

@Component
public class PedidoValidator implements Validator {
	
	private final LivroRepositorio livroRepositorio;
	public PedidoValidator(final LivroRepositorio livroRepositorio) {
		this.livroRepositorio = livroRepositorio;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(PedidoRequest.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		var pedido = (PedidoRequest) target;
		if (!pedido.isValorTotalEstaCorreto(pedido.getTotal(), livroRepositorio)) {
			errors.rejectValue("total", null, "O total informado é inválido.");
		}
		
	}

}
