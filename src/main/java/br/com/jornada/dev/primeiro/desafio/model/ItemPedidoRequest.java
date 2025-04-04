package br.com.jornada.dev.primeiro.desafio.model;

import java.math.BigDecimal;

import br.com.jornada.dev.primeiro.desafio.entidade.ItemPedidoEntidade;
import br.com.jornada.dev.primeiro.desafio.entidade.LivroEntidade;
import br.com.jornada.dev.primeiro.desafio.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.validador.ExistisId;
import jakarta.persistence.NoResultException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemPedidoRequest {

	@NotNull
	@ExistisId(domainClass = LivroEntidade.class, fieldName = "id")
	private Long livro;

	@Positive
	private int quantidade;


	public ItemPedidoRequest(@NotNull Long idLivro, @Positive int quantidade) {
		this.livro = idLivro;
		this.quantidade = quantidade;
	}

	/**
	 * @return the livro
	 */	
	public Long getLivro() {
		return livro;
	}

	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	
	/**
	 * Retorna o preco deste item
	 * @param livroRepositorio
	 * @return
	 */
	public BigDecimal getTotal(final LivroRepositorio livroRepositorio) {
		return livroRepositorio.findById(livro).orElseThrow(NoResultException::new)
				.getPreco()
				.multiply(new BigDecimal(quantidade));
	}

	
	public ItemPedidoEntidade toEntidade(final LivroRepositorio livroRepositorio) {
		return new ItemPedidoEntidade(
				livroRepositorio.findById(livro).orElseThrow(() -> new NoResultException("Livro não cadastrado.")),
				quantidade
		);
	}

	@Override
	public String toString() {
		return "CompraItem [livro=" + livro + ", quantidade=" + quantidade + "]";
	}

}
