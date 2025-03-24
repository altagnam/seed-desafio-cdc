package br.com.jornada.dev.primeiro.desafio.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MessageErroHandle {
	
	private MessageSource messageSource;
	public MessageErroHandle(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrosReponse handleValidationErrors(MethodArgumentNotValidException ex) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toList());
		
		return new ErrosReponse(getErrors(errors));
	}
	
	
	private List<String> getErrors(List<FieldError> errors) {
		List<String> erros = new ArrayList<>();
		for (FieldError error : errors) {
			erros.add(this.messageSource.getMessage(error, LocaleContextHolder.getLocale()));
			
		}
		return erros;
	}
	
	
	public record ErrosReponse(List<String> erros)  {
		
	}

}
