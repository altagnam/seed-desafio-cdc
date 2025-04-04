package br.com.jornada.dev.primeiro.desafio.validador;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.jornada.dev.primeiro.desafio.model.CompraRequest;
import br.com.jornada.dev.primeiro.desafio.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.PaisRepositorio;
import jakarta.persistence.NoResultException;

public class EstadoPertencePaisValidator implements Validator{

	private final PaisRepositorio paisRepositorio;
	private final EstadoRepositorio estadoRepositorio;
	
	
	/**
	 * @param paisRepositorio
	 * @param estadoRepositorio
	 */
	public EstadoPertencePaisValidator(PaisRepositorio paisRepositorio, EstadoRepositorio estadoRepositorio) {
		super();
		this.paisRepositorio = paisRepositorio;
		this.estadoRepositorio = estadoRepositorio;
	}

	@Override
	public boolean supports(Class<?> clazz) {		
		return CompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}		
		
		CompraRequest pedido = (CompraRequest) target;
		if(pedido.isUsuarioInformouEstado()){
			
			var pais = this.paisRepositorio.findById(pedido.getPais()).orElseThrow(() -> new NoResultException("País não encontrado."));			
			if (this.estadoRepositorio.countByPais(pais.getId()) > 0 ) {
				
				var estado = this.estadoRepositorio.findById(pedido.getEstado()).orElseThrow(() -> new NoResultException("Estado não encontrado."));
				if (!estado.isPertencePais(pais)) {
					errors.rejectValue("estado", null, "O estado informado não pertence ao país indicado.");
				}	
			}
		}		
	}

}
