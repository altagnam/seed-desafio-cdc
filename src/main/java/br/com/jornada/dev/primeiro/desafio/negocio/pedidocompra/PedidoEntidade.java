package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.util.Assert;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@jakarta.persistence.Table(name = "PEDIDO")
public class PedidoEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private BigDecimal total;

	@Valid
	@Size(min = 1)
	@OneToMany(cascade = CascadeType.ALL)
	private Set<PedidoItemEntidade> itens;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_compra", referencedColumnName = "id")
	private PedidoCompraEntidade compra;
	
	/**
	 * <p>Construtor utilizado pelo JPA.</p>
	 * Evita o erro <code>org.hibernate.InstantiationException: No default constructor for entity 'PedidoEntidade'</code>
	 */
	@Deprecated
	public PedidoEntidade() {
	
	}

	/**
	 * @param total
	 * @param itens
	 */
	public PedidoEntidade(@NotNull @Valid final PedidoCompraEntidade compra, @NotNull @Positive final BigDecimal total, @NotNull @Size(min = 1) final Set<PedidoItemEntidade> itens) {
		super();
		this.compra = compra;
		this.itens = itens;
		Assert.isTrue(isValorTotalEstaCorreto(total), "O valor informado é diferente do calculado.");
		this.total = total;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
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
	public Set<PedidoItemEntidade> getItens() {
		return itens;
	}

	/**
	 * @return the compra
	 */
	public PedidoCompraEntidade getCompra() {
		return compra;
	}
	
	/**
	 * Verifica se o total do pedido, esta de acordo com o valor calculado pelo sistema
	 * @param valor
	 * @return
	 */
	public boolean isValorTotalEstaCorreto(BigDecimal valor) {
		var totalPedido = getItens().stream()
				.map(PedidoItemEntidade :: getPrecoTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return totalPedido.compareTo(valor) == 0;
	}
	

	@Override
	public String toString() {
		return "PedidoEntidade [id=" + id + ", total=" + total + ", itens=" + itens + ", compra=" + compra + "]";
	}

	

	
}
