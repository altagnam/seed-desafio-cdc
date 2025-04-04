package br.com.jornada.dev.primeiro.desafio.entidade;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@jakarta.persistence.Table(name = "PEDIDO_ITEM")
public class ItemPedidoEntidade {	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Positive
	private int quantidade;
	
	@NotNull
	@Positive
	private BigDecimal precoAtual;
	
	@NotNull
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id", referencedColumnName = "id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "FK_LIVRO_PEDIDO_ITEM"))
	private LivroEntidade livro;

	/**
	 * 
	 * @param livro
	 * @param quantidade
	 */
	public ItemPedidoEntidade(@NotNull LivroEntidade livro, @Positive int quantidade) {
		this.livro = livro;
		this.precoAtual = livro.getPreco();
		this.quantidade = quantidade;
	}
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @return the livro
	 */
	public LivroEntidade getLivro() {
		return livro;
	}

	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * @return the precoAtual
	 */
	public BigDecimal getPrecoAtual() {
		return precoAtual;
	}

	/**
	 * Retorna o preço total
	 * @return
	 */
	public BigDecimal getPrecoTotal() {
		return precoAtual.multiply(new BigDecimal(quantidade));
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(livro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoEntidade other = (ItemPedidoEntidade) obj;
		return Objects.equals(livro, other.livro);
	}

	@Override
	public String toString() {
		return "CompraItem [livro=" + livro + ", quantidade=" + quantidade + ", precoAtual=" + precoAtual + "]";
	}
	
	
	
}
