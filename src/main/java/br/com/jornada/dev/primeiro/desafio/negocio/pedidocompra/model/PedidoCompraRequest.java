package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.negocio.estado.EstadoEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.estado.repository.EstadoRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.livro.repository.LivroRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.PaisEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.repository.PaisRepositorio;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoCompraEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoEntidade;
import br.com.jornada.dev.primeiro.desafio.validador.CpfCnpj;
import br.com.jornada.dev.primeiro.desafio.validador.ExistisId;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PedidoCompraRequest {
	
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
	
	@Valid
	@NotNull
	private PedidoRequest pedido;

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
	public PedidoCompraRequest(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobreNome,
			@NotBlank @CPF @CNPJ String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, Long estado, @NotNull Long pais, @NotBlank String telefone, @NotBlank String cep, @NotNull PedidoRequest pedido) {
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
		this.pedido = pedido;
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
	 * @return the pedido
	 */
	public PedidoRequest getPedido() {
		return pedido;
	}

	/**
	 * Indica se o usuário informou um estado na body da requisição.
	 * @return
	 */
	public boolean isUsuarioInformouEstado() {
		return Objects.nonNull(this.estado);
	}
	
	/**
	 * Retorna uma instancia valida de {@link PedidoCompraEntidade}
	 * @param paisRepositorio
	 * @param estadoRepositorio
	 * @return
	 */
	public PedidoCompraEntidade toEntidade(final PaisRepositorio paisRepositorio, final EstadoRepositorio estadoRepositorio, final LivroRepositorio livroRepositorio) {
		Optional<EstadoEntidade> estadoEncontrado = Optional.empty();
		PaisEntidade paisEncontrado = paisRepositorio.findById(pais).orElseThrow(() -> new NoResultException("País não encontrado."));
		
		if (estadoRepositorio.countByPais(pais) > 0) {
			Assert.notNull(estado, "Estado precisa ser informado.");
			estadoEncontrado = Optional.of(estadoRepositorio.findById(estado).orElseThrow(() -> new NoResultException("Estado não encontrado.")));
		}
		
		Function<PedidoCompraEntidade, PedidoEntidade> funcaoCriacaoPedido = getPedido().toEntidade(livroRepositorio);
		return new PedidoCompraEntidade(email, nome, sobreNome, documento, endereco, complemento, cidade, telefone, cep, estadoEncontrado, paisEncontrado, funcaoCriacaoPedido);		

	}

	@Override
	public String toString() {
		return "SolicitacaoCompraRequest [email=" + email + ", nome=" + nome + ", sobreNome=" + sobreNome
				+ ", documento=" + documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade="
				+ cidade + ", estado=" + estado + ", pais=" + pais + ", telefone=" + telefone + ", cep=" + cep
				+ ", pedido=" + pedido + "]";
	}

}
