package br.com.me.challenge.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.me.challenge.repository.model.PedidoModel;

public class PedidoRowMapper implements RowMapper<PedidoModel> {

	@Override
	public PedidoModel mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final PedidoModel pedido = new PedidoModel();

		pedido.setPedido(rs.getString("pedidoId"));
		pedido.setItensAprovados(rs.getInt("itensAprovados"));
		pedido.setValorAprovado(rs.getInt("valorAprovado"));

		return pedido;
	}

}
