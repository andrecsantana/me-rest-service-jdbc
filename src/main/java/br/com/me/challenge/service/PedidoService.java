package br.com.me.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.me.DataNotFoundException;
import br.com.me.challenge.api.pedido.ItemPedido;
import br.com.me.challenge.api.pedido.Pedido;
import br.com.me.challenge.api.status.StatusRequest;
import br.com.me.challenge.api.status.StatusResponse;
import br.com.me.challenge.repository.ItemPedidoDAO;
import br.com.me.challenge.repository.PedidoDAO;
import br.com.me.challenge.repository.model.ItemPedidoModel;
import br.com.me.challenge.repository.model.PedidoModel;

@Service
public class PedidoService {

	@Autowired
	PedidoDAO pedidoDAO;

	@Autowired
	ItemPedidoDAO itemPedidoDAO;

	public PedidoService() {
	}

	/**
	 * Incluir novo pedido
	 * 
	 * @param request
	 */
	public void incluir(Pedido request) {

		PedidoModel model = new PedidoModel();

		model.setPedido(request.getPedido());

		pedidoDAO.incluirPedido(model);

		addItensDoPedido(request);
	}

	/**
	 * Obtem um pedido
	 * 
	 * @param pedidoId
	 * @return
	 */
	public Pedido obter(String pedidoId) {
		Pedido pedido = new Pedido();

		try {

			PedidoModel pedidoModel = pedidoDAO.localizarPorId(pedidoId);
			List<ItemPedidoModel> items = itemPedidoDAO.getTodosItens(pedidoId);

			pedido.setPedido(pedidoModel.getPedido());

			for (ItemPedidoModel model : items) {
				pedido.getItens().add(new ItemPedido(model));
			}

		} catch (EmptyResultDataAccessException e) {
			throw new DataNotFoundException("Pedido " + pedidoId + " não encontrato");
		}

		return pedido;
	}

	/**
	 * Altera um pedido removendo os seus itens e incluindo os novos itens
	 * 
	 * @param request
	 */
	public void alterar(Pedido request) {
		PedidoModel model = new PedidoModel();
		model.setPedido(request.getPedido());
		model.setItensAprovados(0);
		model.setValorAprovado(0);
		pedidoDAO.atualizarPedido(model);

		this.addItensDoPedido(request);

	}

	/**
	 * 
	 * @param request
	 */
	private void addItensDoPedido(Pedido request) {

		itemPedidoDAO.removerTodosItens(request.getPedido());

		List<ItemPedido> itens = request.getItens();

		int i = 0;
		for (ItemPedido itemRequest : itens) {
			ItemPedidoModel itemModel = new ItemPedidoModel();
			itemModel.setPedido(request.getPedido());
			itemModel.setId(i);
			itemModel.setDescricao(itemRequest.getDescricao());
			itemModel.setPrecoUnitario(itemRequest.getPrecoUnitario());
			itemModel.setQtd(itemRequest.getQtd());
			itemPedidoDAO.incluirItemPedido(itemModel);
			i++;
		}
	}

	public void excluir(String pedidoId) {
		pedidoDAO.removerPedido(pedidoId);
		itemPedidoDAO.removerTodosItens(pedidoId);
	}

	public StatusResponse alterarStatus(StatusRequest request) {
		StatusResponse status = new StatusResponse(request.getPedido());
		PedidoModel pedidoModel = null;
		try {
			pedidoModel = pedidoDAO.localizarPorId(request.getPedido());

			if ("REPROVADO".equals(request.getStatus())) {
				status.getStatus().add("REPROVADO");
				return status;
			}

			pedidoModel.setItensAprovados(request.getItensAprovados());
			pedidoModel.setValorAprovado(request.getValorAprovado());
			pedidoDAO.atualizarPedido(pedidoModel);

			List<ItemPedidoModel> todosItens = itemPedidoDAO.getTodosItens(request.getPedido());

			int valorAprovado = request.getValorAprovado();
			int itensAprovados = request.getItensAprovados();
			boolean pedidoAprovado = "APROVADO".equals(request.getStatus());

			int qtdeTotalItens = 0;
			int valorTotalPedido = 0;

			for (ItemPedidoModel item : todosItens) {
				qtdeTotalItens += item.getQtd();
				valorTotalPedido += (item.getQtd() * item.getPrecoUnitario());
			}

			if (pedidoAprovado) {
				if (itensAprovados == qtdeTotalItens && valorAprovado == valorTotalPedido) {
					status.getStatus().add("APROVADO");
				} else {

					if (valorAprovado < valorTotalPedido) {
						status.getStatus().add("APROVADO_VALOR_A_MENOR");
					}

					if (valorAprovado > valorTotalPedido) {
						status.getStatus().add("APROVADO_VALOR_A_MAIOR");
					}

					if (itensAprovados < qtdeTotalItens) {
						status.getStatus().add("APROVADO_QTD_A_MENOR");
					}

					if (itensAprovados > qtdeTotalItens) {
						status.getStatus().add("APROVADO_QTD_A_MAIOR");
					}
				}

			}

		} catch (EmptyResultDataAccessException e) {

			status.getStatus().add("CODIGO_PEDIDO_INVALIDO");
		}
		return status;

	}

}
