package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.service;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.negocio.estado.EstadoEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.estado.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.PaisEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.repository.PaisRepositorio;
import jakarta.persistence.NoResultException;


/**
 * <p>Responsável por retornar um {@link PaisEntidade} e possivelmente um {@link EstadoEntidade} vinculado ao pais.</p>
 * Com o objetivo de diminuir a carga intrinsica, criamos esta componente 
 * para distribuir a responsabilidade.
 * 
 * @author rafael.altagnam
 *
 */
@Component
public class ConstrutorPaisComEstado {
	//1 UCP
	private PaisRepositorio paisRepositorio;
	
	//1 UCP
	private EstadoRepositorio estadoRepositorio;

	
	/**
	 * 
	 * @param paisRepositorio
	 * @param estadoRepositorio
	 */
	public ConstrutorPaisComEstado(final PaisRepositorio paisRepositorio, final EstadoRepositorio estadoRepositorio) {
		this.paisRepositorio = paisRepositorio;
		this.estadoRepositorio = estadoRepositorio;
	
	}
	
	
	/**
	 * 
	 * @param idPais
	 * @param idEstado
	 * @return
	 */
	//1 UCP
	public PaisComPossivelEstado construir(final Long idPais, final Long idEstado){
		var pais = this.paisRepositorio.findById(idPais).orElseThrow(() -> new NoResultException("País não encontrado."));
		
		//1 UCP
		Optional<EstadoEntidade> possivelEstado = Optional.empty();
		
		//1 UCP
		if (estadoRepositorio.countByPais(idPais) > 0) {
			Assert.notNull(idEstado, "Estado precisa ser informado.");
			possivelEstado = Optional.of(estadoRepositorio.findById(idEstado).orElseThrow(() -> new NoResultException("Estado não encontrado.")));
		}
		
		return new PaisComPossivelEstado(pais, possivelEstado);
	}
	
	public record PaisComPossivelEstado(PaisEntidade pais, Optional<EstadoEntidade> possivelEstado) {}
	
}
