package br.com.me.challenge.api.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class StatusResponse {
	
	private String pedido;
	
	private List<String> status;

	public StatusResponse(String pedido) {
		this.pedido = pedido;
		this.status = new ArrayList<>();
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public List<String> getStatus() {
		return status;
	}

}
