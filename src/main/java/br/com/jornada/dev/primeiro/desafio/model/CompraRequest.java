package br.com.jornada.dev.primeiro.desafio.model;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.entidade.CompraEntidade;
import br.com.jornada.dev.primeiro.desafio.entidade.PedidoCompraEntidade;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CompraRequest {

	@NotNull
	@Positive
	private BigDecimal total;

	@Valid
	@NotNull
	@Size(min = 1)
	private Set<CompraItemRequest> itens;

	/**
	 * @param total
	 * @param itens
	 */
	public CompraRequest(@NotNull @Positive BigDecimal total, @NotNull @Size(min = 1) Set<CompraItemRequest> itens) {
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
	public Set<CompraItemRequest> getItens() {
		return itens;
	}
	
	
	/**
	 * 
	 * @param livroRepositorio
	 * @return
	 */
	public CompraEntidade toEntidade(final PedidoCompraEntidade pedido, final @NotNull LivroRepositorio livroRepositorio) {
		Assert.notNull(livroRepositorio, "O repositorio de livros não deve ser nulo.");
		Assert.isTrue(isValorTotalValido(livroRepositorio), "O total informado esta incorreto.");
		
		var compra = new CompraEntidade(total, pedido);
		var itensComprados = this.itens.stream().map(item -> item.toEntidade(livroRepositorio, compra)).collect(Collectors.toSet());
		compra.setItens(itensComprados);
		return compra;
	}

	/**
	 * Verifica se o valor recebido na requisição, representa o real valor da soma
	 * de todos os livros recebidos
	 * 
	 * @param livroRepositorio
	 * @return
	 */
	public boolean isValorTotalValido(final LivroRepositorio livroRepositorio) {
		var precoTotal = getItens().stream().map(item -> {
			var livro = livroRepositorio.findById(item.getLivro()).orElseThrow(NoResultException::new);
			return livro.getPreco().multiply(new BigDecimal(item.getQuantidade()));
		}).reduce(BigDecimal.ZERO, BigDecimal::add);

		return getTotal().compareTo(precoTotal) == 0;
	}

	@Override
	public String toString() {
		return "CompraRequest [total=" + total + ", itens=" + itens + "]";
	}

}
