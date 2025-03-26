package br.com.jornada.dev.primeiro.desafio.entidade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SolicitacaoCompraEntidade {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Email
	@NotBlank
	@Column(nullable = false)
	private String email;

	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false)
	private String sobreNome;
	
	@NotBlank
	@Column(nullable = false)
	private String documento;
	
	@NotBlank
	@Column(nullable = false)
	private String endereco;
	
	@NotBlank
	@Column(nullable = false)
	private String complemento;
	
	@NotBlank
	@Column(nullable = false)
	private String cidade;
	
	@NotBlank
	@Column(nullable = false)
	private String telefone;
	
	@NotBlank
	@Column(nullable = false)
	private String cep;
	
	@JoinColumn(name = "id_estado")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private EstadoEntidade estado;
	
	@NotNull
	@JoinColumn(name = "id_pais")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private PaisEntidade pais;

	/**
	 * @param email
	 * @param nome
	 * @param sobreNome
	 * @param documento
	 * @param endereco
	 * @param complemento
	 * @param cidade
	 * @param telefone
	 * @param cep
	 * @param estado
	 * @param pais
	 */
	public SolicitacaoCompraEntidade(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobreNome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotBlank String telefone, @NotBlank String cep, EstadoEntidade estado,
			@NotNull PaisEntidade pais) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.telefone = telefone;
		this.cep = cep;
		this.estado = estado;
		this.pais = pais;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the sobreNome
	 */
	public String getSobreNome() {
		return sobreNome;
	}

	/**
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @return the estado
	 */
	public EstadoEntidade getEstado() {
		return estado;
	}

	/**
	 * @return the pais
	 */
	public PaisEntidade getPais() {
		return pais;
	}

	@Override
	public String toString() {
		return "SolicitacaoCompraEntidade [id=" + id + ", email=" + email + ", nome=" + nome + ", sobreNome="
				+ sobreNome + ", documento=" + documento + ", endereco=" + endereco + ", complemento=" + complemento
				+ ", cidade=" + cidade + ", telefone=" + telefone + ", cep=" + cep + ", estado=" + estado + ", pais="
				+ pais + "]";
	}
	
}
