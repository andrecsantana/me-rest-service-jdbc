package br.com.me.challenge.api.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.me.challenge.service.PedidoService;

@RestController
public class PedidosController {

	@Autowired
	PedidoService pedidoService;

	@PostMapping(path = "/pedidos", consumes = "application/json")
	public void incluir(@RequestBody Pedido request) {
		pedidoService.incluir(request);
	}

	@GetMapping(path = "/pedidos/{pedidoId}", produces = "application/json")
	public Pedido obter(@PathVariable String pedidoId) {
		return pedidoService.obter(pedidoId);
	}

	@PutMapping(path = "/pedidos")
	public void alterar(@RequestBody Pedido request) {
		pedidoService.alterar(request);
	}

	@DeleteMapping(path = "/pedidos/{pedidoId}")
	public void excluir(@PathVariable String pedidoId) {
		pedidoService.excluir(pedidoId);
	}

}
