package br.com.me.challenge.repository.model;

public class PedidoModel {

	private String pedido;

	private String status;

	private int itensAprovados;

	private int valorAprovado;

	public PedidoModel() {
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getItensAprovados() {
		return itensAprovados;
	}

	public void setItensAprovados(int itensAprovados) {
		this.itensAprovados = itensAprovados;
	}

	public int getValorAprovado() {
		return valorAprovado;
	}

	public void setValorAprovado(int valorAprovado) {
		this.valorAprovado = valorAprovado;
	}

}
