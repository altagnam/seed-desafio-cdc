package br.com.jornada.dev.primeiro.desafio.validador;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.model.AutorCadastro;
import br.com.jornada.dev.primeiro.desafio.repository.AutorRepositorio;

@Component
public class AutorValidator implements Validator {
	
	private final AutorRepositorio autorRepositorio;
	public AutorValidator(final AutorRepositorio autorRepositorio) {
		this.autorRepositorio = autorRepositorio;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(AutorCadastro.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		var autor = (AutorCadastro) target;
		if (this.autorRepositorio.findByEmail(autor.getEmail()).isPresent()) {
			errors.rejectValue("email", null, "Já existe um usuário cadastrado com este email");
		}
	}

}
