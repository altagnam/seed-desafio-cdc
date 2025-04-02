package br.com.jornada.dev.primeiro.desafio.entidade;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@jakarta.persistence.Table(name = "COMPRA")
public class CompraEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private BigDecimal total;

	@Valid
	@Size(min = 1)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "compra")
	private Set<CompraItemEntidade> itens;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_compra", referencedColumnName = "id")
	private PedidoCompraEntidade pedido;

	/**
	 * @param total
	 * @param itens
	 */
	public CompraEntidade(@NotNull final BigDecimal total, final PedidoCompraEntidade pedido) {
		super();
		this.total = total;
		this.pedido = pedido;
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
	 * 
	 * @param itens
	 */
	public void setItens(@Valid @Min(1) final Set<CompraItemEntidade> itens) {
		this.itens = itens;
	}

	/**
	 * @return the itens
	 */
	public Set<CompraItemEntidade> getItens() {
		return itens;
	}

	/**
	 * @return the pedido
	 */
	public PedidoCompraEntidade getPedido() {
		return pedido;
	}

	
}
