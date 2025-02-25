package br.com.jornada.dev.primeiro.desafio.validador;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.model.CategoriaRequest;
import br.com.jornada.dev.primeiro.desafio.repository.CategoriaRepositorio;

@Component
public class CategoriaValidator implements Validator {
	
	private final CategoriaRepositorio repositorio;
	public CategoriaValidator(final CategoriaRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(CategoriaRequest.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		var item = (CategoriaRequest) target;
		if (this.repositorio.findByNome(item.getNome()).isPresent()) {
			errors.rejectValue("nome", null, "Já existe uma categória cadastrada com este nome.");
		}
	}

}
