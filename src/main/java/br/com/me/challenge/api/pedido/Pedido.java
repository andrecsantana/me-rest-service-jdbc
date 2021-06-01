package br.com.me.challenge.api.pedido;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private String pedido;
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();

	public Pedido() {
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

}
