package br.com.jkavdev.cooperativa.dominio.repositorio;

import java.util.List;

import br.com.jkavdev.cooperativa.dominio.dto.ResumoSessao;

public interface ISessaoRepository {

	public List<ResumoSessao> sessoesEncerradas();

	public void encerrarSessoes(List<Long> ids);

}
