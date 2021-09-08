package br.com.jkavdev.cooperativa.dominio.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jkavdev.cooperativa.dominio.modelo.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

	@Query("SELECT s FROM Sessao s JOIN FETCH s.votos v JOIN FETCH s.pauta p WHERE s.id = ?1")
	public Optional<Sessao> resumo(Long id);

}
