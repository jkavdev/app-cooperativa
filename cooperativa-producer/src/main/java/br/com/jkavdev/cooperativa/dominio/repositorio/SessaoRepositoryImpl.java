package br.com.jkavdev.cooperativa.dominio.repositorio;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.jkavdev.cooperativa.dominio.dto.ResumoSessao;

@Repository
public class SessaoRepositoryImpl implements ISessaoRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ResumoSessao> sessoesEncerradas() {
		return this.jdbcTemplate.query(
				"SELECT s.id, p.descricao, s.inicio, s.fim, count(v.id) " 
						+ "FROM sessao as s "
						+ "JOIN voto as v on s.id=v.sessao_id " 
						+ "JOIN pauta as p on s.pauta_id=p.id "
						+ "WHERE s.fim < CURRENT_TIMESTAMP AND s.status is null " 
						+ "GROUP BY s.id",
				(ResultSet rs, int rowNum) -> new ResumoSessao(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getLong(5)));
	}

	@Override
	public void encerrarSessoes(List<Long> ids) {
		String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
		this.jdbcTemplate.update(String.format("UPDATE sessao set status='FINALIZADA' WHERE id IN (%s)", inSql),
				ids.toArray());
	}

}
