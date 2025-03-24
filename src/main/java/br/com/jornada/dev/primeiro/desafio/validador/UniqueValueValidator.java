package br.com.jornada.dev.primeiro.desafio.validador;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object>{
	
	private String domainField;
	private Class<?> domaninClass;
	private final EntityManager entityManager;
	
	/**
	 * @param entityManager
	 */
	public UniqueValueValidator(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public void initialize(UniqueValue constraintAnnotation) {
		this.domainField = constraintAnnotation.fieldName();
		this.domaninClass = constraintAnnotation.domainClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		StringBuilder query = new StringBuilder("SELECT 1 FROM ")
				.append(domaninClass.getSimpleName())
				.append(" WHERE ").append(domainField).append(" LIKE ")
				.append(":param");
		
		var result = this.entityManager.createQuery(query.toString())
			.setParameter("param", value)
			.getResultList();
		
		Assert.state(result.size() <= 1, new StringBuilder("Foi encontrado mais de um ")
				.append(domaninClass.getName())
				.append(" com o valor ")
				.append(domainField)
				.toString()
		);		
		
		return result.isEmpty();
	}

}
