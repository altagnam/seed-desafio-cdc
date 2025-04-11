package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.negocio.livro.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoRequest;

@Component
public class PedidoCompraVerificarPedidoValidator implements Validator {
	
	private final LivroRepositorio livroRepositorio;
	public PedidoCompraVerificarPedidoValidator(final LivroRepositorio livroRepositorio) {
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
