package br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.model;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;

import br.com.jornada.dev.primeiro.desafio.negocio.estado.EstadoEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pais.PaisEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.PedidoCompraEntidade;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.service.ConstrutorPaisComEstado;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.service.ConstrutorPaisComEstado.PaisComPossivelEstado;
import br.com.jornada.dev.primeiro.desafio.negocio.pedidocompra.service.GeradorPedido;
import br.com.jornada.dev.primeiro.desafio.validador.CpfCnpj;
import br.com.jornada.dev.primeiro.desafio.validador.ExistisId;
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
	
	private String cupom;
	
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
	 * @param pedido
	 * @param cupom
	 */
	public PedidoCompraRequest(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobreNome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, Long estado, @NotNull Long pais, @NotBlank String telefone, @NotBlank String cep,
			@Valid @NotNull PedidoRequest pedido, String cupom) {
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
		this.cupom = cupom;
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
	 * 
	 * @return
	 */
	public BigDecimal getTotalPedido() {
		return pedido.getTotal();
	}

	/**
	 * @return the cupom
	 */
	public String getCupom() {
		return cupom;
	}
	
	/**
	 * Indica se há cupom informado para este pedido
	 * @return
	 */
	public boolean isCupomInformado() {
		return !Strings.isBlank(cupom);
	}

	/**
	 * Indica se o usuário informou um estado na body da requisição.
	 * @return
	 */
	public boolean isUsuarioInformouEstado() {
		return Objects.nonNull(this.estado);
	}
	
	
	/**
	 * 
	 * @param geradorPedido
	 * @param construtorPaisComEstado
	 * @return
	 */
	// 1 UCP GeradorPedido
	// 1 UCP ConstrutorPaisComEstado
	public PedidoCompraEntidade toEntidade(final GeradorPedido geradorPedido, final ConstrutorPaisComEstado construtorPaisComEstado) {
		//1 UCP PaisComPossivelEstado
		PaisComPossivelEstado paisEstado = construtorPaisComEstado.construir(pais, estado);
		
		//1 UCP PedidoCompraEntidade
		PedidoCompraEntidade pedidoCompra = geradorPedido.gerar(this, paisEstado);
		return pedidoCompra;

	}

	@Override
	public String toString() {
		return "SolicitacaoCompraRequest [email=" + email + ", nome=" + nome + ", sobreNome=" + sobreNome
				+ ", documento=" + documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade="
				+ cidade + ", estado=" + estado + ", pais=" + pais + ", telefone=" + telefone + ", cep=" + cep
				+ ", pedido=" + pedido + "]";
	}

}
