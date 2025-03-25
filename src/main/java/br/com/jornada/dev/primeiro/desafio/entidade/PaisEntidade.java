package br.com.jornada.dev.primeiro.desafio.entidade;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

/**
 * <p>
 * Classe para realizar a persistencia de um Pais.
 * </p>

 * 
 * @author rafael.altagnam
 * @desafio 1
 */
@Entity
@jakarta.persistence.Table(name = "PAIS")
public class PaisEntidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(name = "NOME", nullable = false, unique = true)
	private String nome;
	
	

	/**
	 * <p>Construtor de uso obrigatório e exclusvivo para instanciar o objeto no JPA. <br/><b>Evite de utiliza-lo.</b></p>
	 * Sem o construtor temos o erro: <code>org.hibernate.InstantiationException: No default constructor for entity</code>
	 *
	 */
	@Deprecated
	public PaisEntidade() {
		super();
	}
	

	/**
	 * @param id
	 * @param nome
	 */
	public PaisEntidade(@NotEmpty String nome) {
		super();
		this.nome = nome;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "PaisEntidade [id=" + id + ", nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaisEntidade other = (PaisEntidade) obj;
		return Objects.equals(nome, other.nome);
	}
	
	

}
