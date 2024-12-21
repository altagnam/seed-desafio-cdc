package br.com.jornada.dev.primeiro.desafio.validador;

import java.util.regex.Pattern;

public class EmailValidator {
	
	private static  final String EMAIL_REGEX = "^(.+)@(\\S+)$";
	public static boolean validar(final String email) {
		 return Pattern.compile(EMAIL_REGEX)
			      .matcher(email)
			      .matches();
	}

}
