package br.com.jornada.dev.primeiro.desafio.negocio.cupom.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.jornada.dev.primeiro.desafio.negocio.cupom.CupomEntidade;
import br.com.jornada.dev.primeiro.desafio.validador.UniqueValue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * <p>
 * Classe responsável por representar a solicitação de cadastro de um novo cupom
 * </p>
 * Todo cupom tem:
 * <ul>
 * <li>um código para ser entendido por pessoas</li>
 * <li> um percentual de desconto</li>
 * <li>uma validade para ser associado a uma compra</li>
 * </ul>
 * 
 * @author rafael.altaganm
 *
 */
public class NovoCupomRequest {
	
	@NotBlank
	@UniqueValue(fieldName = "codigo", domainClass = CupomEntidade.class)
	private String codigo;
	
	@Positive
	private double desconto;
	
	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate validade;

	/**
	 * @param codigo
	 * @param desconto
	 * @param validade
	 */
	public NovoCupomRequest(@NotBlank String codigo, @Positive double desconto, @Future LocalDate validade) {
		super();
		this.codigo = codigo;
		this.desconto = desconto;
		this.validade = validade;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public CupomEntidade toEntidade() {
		return new CupomEntidade(codigo, desconto, validade);
		
	}

	@Override
	public String toString() {
		return "NovoCupomRequest [codigo=" + codigo + ", desconto=" + desconto + ", validade=" + validade + "]";
	}
	
	

}
