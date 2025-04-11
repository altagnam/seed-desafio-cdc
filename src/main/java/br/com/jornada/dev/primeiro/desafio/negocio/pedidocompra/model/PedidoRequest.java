package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model;

import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.negocio.livro.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoCompraEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoItemEntidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PedidoRequest {

	@NotNull
	@Positive
	private BigDecimal total;

	@Valid
	@NotNull
	@Size(min = 1)
	private Set<PedidoItemRequest> itens;

	/**
	 * @param total
	 * @param itens
	 */
	public PedidoRequest(@NotNull @Positive BigDecimal total, @NotNull @Size(min = 1) Set<PedidoItemRequest> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}

	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * @return the itens
	 */
	public Set<PedidoItemRequest> getItens() {
		return itens;
	}
	
	/**
	 * Verifica se o total do pedido, esta de acordo com o valor calculado pelo sistema
	 * @param valor
	 * @return
	 */
	public boolean isValorTotalEstaCorreto(final @NotNull BigDecimal valor, final @NotNull LivroRepositorio livroRepositorio) {
		var totalPedido = getItens().stream()
				.map(item -> item.getTotal(livroRepositorio))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return totalPedido.compareTo(valor) == 0;
	}
	
	/**
	 * 
	 * @param livroRepositorio
	 * @return
	 */
	public Function<PedidoCompraEntidade, PedidoEntidade> toEntidade(final @NotNull LivroRepositorio livroRepositorio) {
		Assert.notNull(livroRepositorio, "O repositorio de livros não deve ser nulo.");		
		Set<PedidoItemEntidade> itensComprados = this.itens.stream().map(item -> item.toEntidade(livroRepositorio)).collect(Collectors.toSet());
		
		return (compra) -> {
			return new PedidoEntidade(compra, total, itensComprados);
		};		
	}


	@Override
	public String toString() {
		return "CompraRequest [total=" + total + ", itens=" + itens + "]";
	}

}
