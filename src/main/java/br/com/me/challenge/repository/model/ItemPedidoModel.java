package br.com.me.challenge.repository.model;

public class ItemPedidoModel {

	private int id;

	private String pedido;

	private String descricao;

	private int precoUnitario;

	private int qtd;

	public ItemPedidoModel() {
	}



	public ItemPedidoModel(int id, String pedido, String descricao, int precoUnitario, int qtd) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.descricao = descricao;
		this.precoUnitario = precoUnitario;
		this.qtd = qtd;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(int precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

}
