package br.com.me.challenge.api.pedido;

import br.com.me.challenge.repository.model.ItemPedidoModel;

public class ItemPedido {
	String descricao;
	int precoUnitario;
	int qtd;

	public ItemPedido() {
	}

	public ItemPedido(ItemPedidoModel model) {
		this.descricao = model.getDescricao();
		this.precoUnitario = model.getPrecoUnitario();
		this.qtd = model.getQtd();
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
