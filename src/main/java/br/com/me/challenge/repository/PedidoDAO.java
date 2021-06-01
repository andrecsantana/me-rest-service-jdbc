package br.com.me.challenge.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.me.challenge.repository.model.PedidoModel;

@Repository
public class PedidoDAO {

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("TB_PEDIDO");
	}

	public int incluirPedido(final PedidoModel pedido) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("pedidoId", pedido.getPedido());
		parameters.put("itensAprovados", pedido.getItensAprovados());
		parameters.put("valorAprovado", pedido.getValorAprovado());
		
		return simpleJdbcInsert.execute(parameters);
	}

	public void atualizarPedido(final PedidoModel pedido) {
		final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", pedido.getPedido())
				.addValue("itens", pedido.getItensAprovados()).addValue("valor", pedido.getValorAprovado());
		
		namedParameterJdbcTemplate.update(
				"UPDATE TB_PEDIDO SET itensAprovados =:itens, valorAprovado =:valor WHERE pedidoId = :id",
				namedParameters);
	}

	public PedidoModel localizarPorId(String pedidoId) {
		final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", pedidoId);
		return namedParameterJdbcTemplate.queryForObject("SELECT * FROM TB_PEDIDO WHERE pedidoId = :id",
				namedParameters, new PedidoRowMapper());
	}

	public List<PedidoModel> getTodosPedidos() {
		return jdbcTemplate.query("SELECT * FROM TB_PEDIDO", new PedidoRowMapper());
	}


	public void removerPedido(String pedidoId) {
		final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", pedidoId);
		namedParameterJdbcTemplate.update("DELETE FROM TB_PEDIDO WHERE pedidoId = :id", namedParameters);
	}

}
