package br.com.jornada.dev.primeiro.desafio.validador;

import java.util.Objects;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsIdValidator implements ConstraintValidator<ExistisId, Long> {

	private String domainField;
	private Class<?> domaninClass;
	private final EntityManager entityManager;
	
	/**
	 * @param entityManager
	 */
	public ExistsIdValidator(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	@Override
	public void initialize(ExistisId constraintAnnotation) {
		this.domainField = constraintAnnotation.fieldName();
		this.domaninClass = constraintAnnotation.domainClass();
	}


	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		boolean valid = true;
		if (Objects.isNull(value)) {
			return valid;			
		}
		
		try {
			StringBuilder query = new StringBuilder("SELECT 1 FROM ")
					.append(domaninClass.getSimpleName())
					.append(" WHERE ").append(domainField).append(" = ")
					.append(":param");
			
			this.entityManager.createQuery(query.toString())
				.setParameter("param", value)
				.getSingleResult();
			
		
		}catch (NoResultException e) {
			valid = false;
		}
		
		return valid;
	}

}
