/**
 * 
 */
package br.com.jornada.dev.primeiro.desafio.model;

import java.time.LocalDateTime;

import br.com.jornada.dev.primeiro.desafio.entidade.AutorEntidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * <p>Classe de entrada para realizar o cadastro de um Autor. </p>
 * <p>Todo autor tem um nome,
 * email e uma descrição. Também queremos saber o instante exato que ele foi
 * registrado.
 * </p>
 * 
 * @author rafael.altagnam
 * @desafio 1
 */
public class AutorRequest {

	@NotNull(message = "Nome é obrigatório")
	private String nome;

	@NotNull(message = "E-mail é obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	@NotNull(message = "Descrição é obrigatório")
	@Size(max = 400, message = "A descrição deve possuir no máximo 400 caracteres")
	private String descricao;

	private LocalDateTime instante;


	public AutorRequest(String nome, String descricao, String email) {
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
