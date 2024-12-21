/**
 * 
 */
package br.com.jornada.dev.primeiro.desafio.model;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

import br.com.jornada.dev.primeiro.desafio.entidade.AutorEntidade;
import br.com.jornada.dev.primeiro.desafio.validador.EmailValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * <p>Todo autor tem um nome,
 * email e uma descrição. Também queremos saber o instante exato que ele foi
 * registrado.
 * </p>
 * 
 * @author rafael.altagnam
 * @desafio 1
 */
public class Autor {

	@NotNull(message = "Nome é obrigatório")
	private String nome;

	@NotNull(message = "E-mail é obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	@NotNull(message = "Descrição é obrigatório")
	@Size(min = 1, max = 400, message = "Tamanho permitido: 1 a 400 caracteres")
	private String descricao;

	private LocalDateTime instante;


	public Autor(String nome, String descricao, String email) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.email = email;
		this.instante = LocalDateTime.now();
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the instante
	 */
	public LocalDateTime getInstante() {
		return instante;
	}
	
	
	public AutorEntidade toEntidade() {
		Assert.hasText(this.nome, "Nome é obrigatório");
		Assert.hasText(this.email, "E-mail é obrigatório");
		Assert.isTrue(EmailValidator.validar(this.email), "E-mail inválido");
		Assert.hasText(this.descricao, "Descrição é obrigatório");
		Assert.notNull(this.instante, "A data de crição é obrigatória");
		return new AutorEntidade(
				this.nome, 
				this.email, 
				this.descricao, 
				this.instante
		);
	}
	

	@Override
	public String toString() {
		return "Autor [nome=" + nome + ", descricao=" + descricao + ", email=" + email + ", instante=" + instante + "]";
	}

}
