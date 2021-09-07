package br.com.jkavdev.cooperativa.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.jkavdev.cooperativa.dominio.modelo.Pauta;
import br.com.jkavdev.cooperativa.dominio.repositorio.PautaRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;

	@PostMapping
	public Pauta adicionar(String descricao) {
		Pauta pauta = new Pauta();
		pauta.setDescricao(descricao);

		return pautaRepository.save(pauta);
	}

}
