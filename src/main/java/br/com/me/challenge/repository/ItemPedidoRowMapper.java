package br.com.me.challenge.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.me.challenge.repository.model.ItemPedidoModel;

public class ItemPedidoRowMapper implements RowMapper<ItemPedidoModel> {

	@Override
	public ItemPedidoModel mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final ItemPedidoModel item = new ItemPedidoModel();

		item.setPedido(rs.getString("pedidoId"));
		item.setId(rs.getInt("ItemId"));
		item.setDescricao(rs.getString("descricao"));
		item.setPrecoUnitario(rs.getInt("precoUnitario"));
		item.setQtd(rs.getInt("qtd"));

		return item;
	}

}
