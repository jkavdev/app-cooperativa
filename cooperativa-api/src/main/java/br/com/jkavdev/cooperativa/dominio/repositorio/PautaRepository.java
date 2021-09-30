package br.com.jkavdev.cooperativa.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jkavdev.cooperativa.dominio.modelo.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
