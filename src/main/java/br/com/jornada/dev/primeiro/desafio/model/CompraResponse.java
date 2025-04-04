package br.com.jornada.dev.primeiro.desafio.model;

public class CompraResponse {

	private Long id;

	private SituacaoCompra status;

	/**
	 * @param id
	 * @param status
	 */
	public CompraResponse(Long id, SituacaoCompra status) {
		super();
		this.id = id;
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the status
	 */
	public SituacaoCompra getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "PedidoCompraRequest [id=" + id + ", status=" + status + "]";
	}

	public enum SituacaoCompra {
		INICIADA, EM_ANALISE, FINALIZADA
	}

}
