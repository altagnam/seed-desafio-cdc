package br.com.jornada.dev.primeiro.desafio.negocio.cupom;

import java.time.LocalDate;

import org.springframework.util.Assert;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "CUPOM")
public class CupomEntidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String codigo;
	
	@Positive
	private double desconto;
	
	@Future
	private LocalDate validade;

	/**
	 * @param codigo
	 * @param desconto
	 * @param validade
	 */
	public CupomEntidade(@NotBlank String codigo, @Positive double desconto, @Future LocalDate validade) {
		super();
		Assert.hasText(codigo, "O código não foi informado corretamente");
		Assert.isTrue(desconto > 0, "O desconto precisa ser maior que zero.");
		Assert.isTrue(validade.isAfter(LocalDate.now()), "A data de validade precisa ser uma data futura.");
		this.codigo = codigo;
		this.desconto = desconto;
		this.validade = validade;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return the desconto
	 */
	public double getDesconto() {
		return desconto;
	}

	/**
	 * @return the validade
	 */
	public LocalDate getValidade() {
		return validade;
	}
	
	
	@Override
	public String toString() {
		return "CupomEntidade [id=" + id + ", codigo=" + codigo + ", desconto=" + desconto + ", validade=" + validade
				+ "]";
	}
	

}
