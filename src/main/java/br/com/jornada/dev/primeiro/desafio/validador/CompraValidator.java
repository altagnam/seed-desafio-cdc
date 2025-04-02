package br.com.jornada.dev.primeiro.desafio.validador;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.model.PedidoCompraRequest;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;

@Component
public class CompraValidator implements Validator {
	
	private final LivroRepositorio livroRepositorio;
	public CompraValidator(final LivroRepositorio livroRepositorio) {
		this.livroRepositorio = livroRepositorio;
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
		
		var solicitacao = (PedidoCompraRequest) target;
		if (!solicitacao.getCompra().isValorTotalValido(livroRepositorio)) {
			errors.setNestedPath("compra");
			errors.rejectValue("total", null, "O total informado é inválido.");
		}
		
	}

}
