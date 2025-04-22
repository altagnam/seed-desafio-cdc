package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoCompraEntidade;
import io.micrometer.common.lang.NonNull;

@JsonInclude(Include.NON_NULL)
public class CompraRealizadaResponse {

	private boolean temCupom;	
	private BigDecimal valorOriginal;
	private BigDecimal valorCupomAplicado;
	
	/**
	 * @param temCupom
	 * @param valorOriginal
	 * @param valorCupomAplicado
	 */
	public CompraRealizadaResponse(@NonNull final PedidoCompraEntidade compra) {
		Assert.notNull(compra, "Compra não informada.");
		this.temCupom = compra.temCupom();
		this.valorOriginal = compra.getTotalOriginal();
		this.valorCupomAplicado = compra.getTotalCupomAplicado();
	
	}

	/**
	 * @return the temCupom
	 */
	public boolean isTemCupom() {
		return temCupom;
	}

	/**
	 * @return the valorOriginal
	 */
	public BigDecimal getValorOriginal() {
		return valorOriginal;
	}

	/**
	 * @return the valorCupomAplicado
	 */
	public BigDecimal getValorCupomAplicado() {
		return valorCupomAplicado;
	}
	

	@Override
	public String toString() {
		return "CompraRealizadaResponse [temCupom=" + temCupom + ", valorOriginal=" + valorOriginal
				+ ", valorCupomAplicado=" + valorCupomAplicado + "]";
	}
	
	
	
}
