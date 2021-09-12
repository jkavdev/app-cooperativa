package br.com.jkavdev.cooperativa.dominio.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jkavdev.cooperativa.dominio.dto.SessaoModel;
import br.com.jkavdev.cooperativa.dominio.modelo.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

	@Query("SELECT s FROM Sessao s JOIN FETCH s.votos v JOIN FETCH s.pauta p WHERE s.id = ?1")
	public Optional<Sessao> resumo(Long id);

	@Query("SELECT new br.com.jkavdev.cooperativa.dominio.dto.SessaoModel(s.id, p.descricao, s.inicio, s.fim"
			+ ", count(v.id))"
			+ "FROM Sessao s "
			+ "JOIN s.votos v "
			+ "JOIN s.pauta p "
			+ "WHERE s.fim < CURRENT_TIMESTAMP "
			+ "GROUP BY s.id ")
	public List<SessaoModel> sessoesEncerradas();

}
