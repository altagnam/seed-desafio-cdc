package br.com.jornada.dev.primeiro.desafio.entidade;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * Classe para realizar a persistencia de um Estado.
 * </p>

 * 
 * @author rafael.altagnam
 * @desafio 1
 */
@Entity
@jakarta.persistence.Table(name = "ESTADO")
public class EstadoEntidade {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pais")
	private PaisEntidade pais;
	
	

	/**
	 * <p>Utilizado pelo JPA</p>
	 * Evita o erro <br/>
	 * <code>org.hibernate.InstantiationException: No default constructor for entity 'br.com.jornada.dev.primeiro.desafio.entidade.EstadoEntidade'</code><br/>
	 * ao tentar instanciar esta entidade via jpa
	 */
	@Deprecated
	public EstadoEntidade() {
		super();
	}

	/**
	 * @param nome
	 * @param pais
	 */
	public EstadoEntidade(@NotEmpty String nome, @NotNull PaisEntidade pais) {
		super();
		this.nome = nome;
		this.pais = pais;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the pais
	 */
	public PaisEntidade getPais() {
		return pais;
	}

	/**
	 * 
	 * @param pais
	 * @return
	 */
	public boolean isPertencePais(final PaisEntidade pais) {
		return this.pais.equals(pais);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(nome, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoEntidade other = (EstadoEntidade) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(pais, other.pais);
	}

	@Override
	public String toString() {
		return "EstadoEntidade [id=" + id + ", nome=" + nome + ", pais=" + pais + "]";
	}

	
	

}
