package br.com.jkavdev.cooperativa.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jkavdev.cooperativa.dominio.modelo.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

}
