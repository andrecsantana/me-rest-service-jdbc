package br.com.me.challenge.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.me.challenge.repository.model.ItemPedidoModel;

@Repository
public class ItemPedidoDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void setDataSource(final DataSource dataSource) {

		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("TB_ITEM_PEDIDO");
	}

	public List<ItemPedidoModel> getTodosItens(String pedidoId) {
		final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", pedidoId);
		return namedParameterJdbcTemplate.query("SELECT * FROM TB_ITEM_PEDIDO WHERE pedidoId = :id", namedParameters,
				new ItemPedidoRowMapper());
	}

	public void removerTodosItens(String pedidoId) {
		final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", pedidoId);
		namedParameterJdbcTemplate.update("DELETE FROM TB_ITEM_PEDIDO WHERE pedidoId = :id", namedParameters);
	}

	public int incluirItemPedido(final ItemPedidoModel item) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("pedidoId", item.getPedido());
		parameters.put("itemId", item.getId());
		parameters.put("descricao", item.getDescricao());
		parameters.put("precoUnitario", item.getPrecoUnitario());
		parameters.put("qtd", item.getQtd());

		return simpleJdbcInsert.execute(parameters);
	}

}
