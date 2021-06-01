package br.com.me.challenge.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.me.challenge.api.status.StatusRequest;
import br.com.me.challenge.api.status.StatusResponse;
import br.com.me.challenge.repository.ItemPedidoDAO;
import br.com.me.challenge.repository.PedidoDAO;
import br.com.me.challenge.repository.model.ItemPedidoModel;
import br.com.me.challenge.repository.model.PedidoModel;

@SpringBootTest
class PedidoServiceTest {

	@MockBean
	PedidoDAO pedidoDAO;

	@MockBean
	ItemPedidoDAO itemPedidoDAO;
	
	@Autowired
	PedidoService pedidoService;
	
	@Test
	void testCodigoPedidoInvalido() {

		when(pedidoDAO.localizarPorId(anyString())).thenThrow(new EmptyResultDataAccessException(1));
		
		
		StatusRequest request = new StatusRequest();
		request.setPedido("123456");
		request.setStatus("REPROVADO");
		StatusResponse statusResponse = pedidoService.alterarStatus(request);
		
		assertTrue("CODIGO_PEDIDO_INVALIDO".equals(statusResponse.getStatus().get(0)));
	}

	
	@Test
	void testReprovado() {

		PedidoModel pedidoModel = Mockito.mock(PedidoModel.class);
		when(pedidoDAO.localizarPorId(anyString())).thenReturn(pedidoModel);
		
		
		StatusRequest request = new StatusRequest();
		request.setPedido("123456");
		request.setStatus("REPROVADO");
		StatusResponse statusResponse = pedidoService.alterarStatus(request);
		
		assertTrue("REPROVADO".equals(statusResponse.getStatus().get(0)));
	}
	
	@Test
	void testAprovado() {

		PedidoModel pedidoModel = Mockito.mock(PedidoModel.class);
		when(pedidoDAO.localizarPorId(anyString())).thenReturn(pedidoModel);
		List<ItemPedidoModel> listaItensPedido = new ArrayList<>();
		
		listaItensPedido.add(new ItemPedidoModel(1, "123456", "Item A", 10, 1));
		listaItensPedido.add(new ItemPedidoModel(2, "123456", "Item B", 5, 2));
		
		when(itemPedidoDAO.getTodosItens(anyString())).thenReturn(listaItensPedido);
		
		
		StatusRequest request = new StatusRequest();
		request.setPedido("123456");
		request.setStatus("APROVADO");
		request.setItensAprovados(3);
		request.setValorAprovado(20);
		
		StatusResponse statusResponse = pedidoService.alterarStatus(request);
		
		assertTrue("APROVADO".equals(statusResponse.getStatus().get(0)));
	}	
	
}
