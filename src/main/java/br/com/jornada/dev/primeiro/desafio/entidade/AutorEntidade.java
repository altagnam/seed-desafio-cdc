package br.com.jornada.dev.primeiro.desafio.entidade;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.model.AutorResponse;
import br.com.jornada.dev.primeiro.desafio.validador.EmailValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@jakarta.persistence.Table(name = "AUTOR")
public class AutorEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "NOME", nullable = false)
	private String nome;

	@NotNull
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@NotNull
	@Column(name = "DESCRICAO", nullable = false, length = 400)
	private String descricao;

	@NotNull
	@Column(name = "INSTANTE", nullable = false)
	private LocalDateTime instante;

	/**
	 * <p>
	 * Construtor deprecated;
	 * </p>
	 * Necessário para hibernate
	 */
	@Deprecated
	protected AutorEntidade() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nome
	 * @param email
	 * @param descricao
	 * @param instante
	 */
	public AutorEntidade(@NotNull String nome, @NotNull @Email String email,
			@NotNull @Size(min = 1, max = 400) String descricao, LocalDateTime instante) {
		super();
		Assert.hasText(nome, "Nome é obrigatório");
		Assert.hasText(email, "E-mail é obrigatório");
		Assert.isTrue(EmailValidator.validar(email), "E-mail inválido");
		Assert.hasText(descricao, "Descrição é obrigatório");
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.instante = instante;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the instante
	 */
	public LocalDateTime getInstante() {
		return instante;
	}

	/**
	 * @param instante the instante to set
	 */
	public void setInstante(LocalDateTime instante) {
		this.instante = instante;
	}
	
	public AutorResponse toAutorCadastrado() {
		return new AutorResponse(this.id, this.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutorEntidade other = (AutorEntidade) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "AutorEntidade [id=" + id + ", nome=" + nome + ", email=" + email + ", descricao=" + descricao
				+ ", instante=" + instante + "]";
	}

}
