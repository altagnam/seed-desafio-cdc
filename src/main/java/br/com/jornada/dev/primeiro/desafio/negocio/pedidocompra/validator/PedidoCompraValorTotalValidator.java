package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.negocio.livro.repository.LivroRepository;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model.PedidoRequest;

@Component
public class PedidoCompraValorTotalValidator implements Validator {
	
	private final LivroRepository livroRepositorio;
	public PedidoCompraValorTotalValidator(final LivroRepository livroRepositorio) {
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
