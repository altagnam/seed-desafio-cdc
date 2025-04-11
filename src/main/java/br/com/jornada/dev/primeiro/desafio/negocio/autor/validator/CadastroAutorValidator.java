package br.com.jornada.dev.primeiro.desafio.negocio.autor.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.negocio.autor.AutorEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.autor.model.AutorRequest;
import br.com.jornada.dev.primeiro.desafio.negocio.autor.repository.AutorRepositorio;

/**
 * Responsável por validar a body request enviada pelo cliente, com o intuito de realizar o cadastro de um {@link AutorEntidade} 
 * @author rafael.altagnam
 *
 */
@Component
public class CadastroAutorValidator implements Validator {
	
	private final AutorRepositorio autorRepositorio;
	public CadastroAutorValidator(final AutorRepositorio autorRepositorio) {
		this.autorRepositorio = autorRepositorio;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(AutorRequest.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		var autor = (AutorRequest) target;
		if (this.autorRepositorio.findByEmail(autor.getEmail()).isPresent()) {
			errors.rejectValue("email", null, "Já existe um autor cadastrado com este email");
		}
	}

}
