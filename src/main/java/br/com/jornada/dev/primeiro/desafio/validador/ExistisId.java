package br.com.jornada.dev.primeiro.desafio.validador;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistsIdValidator.class})
public @interface ExistisId {
	
	String message() default "{validator.idnotfound}";
	
	Class<?> [] groups() default {};
	
	Class<? extends Payload> [] payload() default {};
	
	String fieldName();
	
	Class<?> domainClass();
	

}
