package br.com.jornada.dev.primeiro.desafio.model;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.entidade.EstadoEntidade;
import br.com.jornada.dev.primeiro.desafio.entidade.PaisEntidade;
import br.com.jornada.dev.primeiro.desafio.entidade.SolicitacaoCompraEntidade;
import br.com.jornada.dev.primeiro.desafio.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.repository.PaisRepositorio;
import br.com.jornada.dev.primeiro.desafio.validador.CpfCnpj;
import br.com.jornada.dev.primeiro.desafio.validador.ExistisId;
import jakarta.persistence.NoResultException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SolicitacaoCompraRequest {
	
	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobreNome;
	
	@NotBlank
	@CpfCnpj
	private String documento;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String cidade;
	
	@ExistisId(domainClass = EstadoEntidade.class, fieldName = "id")
	private Long estado;
	
	@NotNull
	@ExistisId(domainClass = PaisEntidade.class, fieldName = "id")
	private Long pais;
	
	@NotBlank
	private String telefone;
	
	@NotBlank
	private String cep;

	/**
	 * @param email
	 * @param nome
	 * @param sobreNome
	 * @param documento
	 * @param endereco
	 * @param complemento
	 * @param cidade
	 * @param estado
	 * @param pais
	 * @param telefone
	 * @param cep
	 */
	public SolicitacaoCompraRequest(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobreNome,
			@NotBlank @CPF @CNPJ String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, Long estado, @NotNull Long pais, @NotBlank String telefone, @NotBlank String cep) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.telefone = telefone;
		this.cep = cep;
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
	 * @return the estado
	 */
	public Long getEstado() {
		return estado;
	}

	/**
	 * @return the pais
	 */
	public Long getPais() {
		return pais;
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
	 * Retorna uma instancia valida de {@link SolicitacaoCompraEntidade}
	 * @param paisRepositorio
	 * @param estadoRepositorio
	 * @return
	 */
	public SolicitacaoCompraEntidade toEntidade(final PaisRepositorio paisRepositorio, final EstadoRepositorio estadoRepositorio) {
		EstadoEntidade estadoEncontrado = null;
		PaisEntidade paisEncontrado = paisRepositorio.findById(pais).orElseThrow(() -> new NoResultException("País não encontrado."));
		
		if (estadoRepositorio.countByPais(pais) > 0) {
			Assert.notNull(estado, "Estado não informado.");
			estadoEncontrado = estadoRepositorio.findById(estado).orElseThrow(() -> new NoResultException("Estado não encontrado."));
		}
		
		return new SolicitacaoCompraEntidade(email, nome, sobreNome, documento, endereco, complemento, cidade, telefone, cep, estadoEncontrado, paisEncontrado);
	}

	@Override
	public String toString() {
		return "SolicitacaoCompraParcial [email=" + email + ", nome=" + nome + ", sobreNome=" + sobreNome
				+ ", documento=" + documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade="
				+ cidade + ", estado=" + estado + ", pais=" + pais + ", telefone=" + telefone + ", cep=" + cep + "]";
	}
	
	

}
