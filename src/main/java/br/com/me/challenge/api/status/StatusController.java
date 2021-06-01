package br.com.me.challenge.api.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.me.challenge.repository.PedidoDAO;
import br.com.me.challenge.service.PedidoService;

@RestController
public class StatusController {

	@Autowired
	PedidoService pedidoService;

	@Autowired
	PedidoDAO pedidoDAO;

	@PostMapping(path = "/status", consumes = "application/json", produces = "application/json")
	public StatusResponse alteraStatus(@RequestBody StatusRequest request) {
		return pedidoService.alterarStatus(request);
	}

}
