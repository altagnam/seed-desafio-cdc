package br.com.jornada.dev.primeiro.desafio.validador;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.model.CompraItemRequest;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;

@Component
public class CompraItemValidator implements Validator {
	
	private final LivroRepositorio livroRepositorio;
	public CompraItemValidator(final LivroRepositorio livroRepositorio) {
		this.livroRepositorio = livroRepositorio;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(CompraItemRequest.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		var item = (CompraItemRequest) target;
		if (item.getQuantidade() < 1) {
			errors.rejectValue("quantidade", null, "Quantidade deve ser maior que zero");
		}
		
		if(!livroRepositorio.existsById(item.getLivro())) {
			errors.rejectValue("livro", null, "Livro não cadastrado");
		}
		
	}

}
