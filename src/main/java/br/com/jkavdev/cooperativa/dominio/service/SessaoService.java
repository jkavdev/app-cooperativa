package br.com.jkavdev.cooperativa.dominio.service;

import java.time.Duration;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jkavdev.cooperativa.dominio.exception.EntidadeNaoEncontradaException;
import br.com.jkavdev.cooperativa.dominio.exception.NegocioException;
import br.com.jkavdev.cooperativa.dominio.modelo.Pauta;
import br.com.jkavdev.cooperativa.dominio.modelo.Sessao;
import br.com.jkavdev.cooperativa.dominio.modelo.Voto;
import br.com.jkavdev.cooperativa.dominio.repositorio.SessaoRepository;
import br.com.jkavdev.cooperativa.externo.CpfVerificador;
import br.com.jkavdev.cooperativa.externo.StatusUsuario;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository sessaoRepository;

	@Autowired
	private PautaService pautaService;

	@Autowired
	private CpfVerificador cpfVerificador;

	public Sessao buscar(Long sessaoId) {
		return sessaoRepository.findById(sessaoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("sessao nao encontrada"));
	}

	public Sessao resumo(Long sessaoId) {
		return sessaoRepository.resumo(sessaoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("sessao nao encontrada"));
	}

	@Transactional
	public Sessao abrir(Long pautaId, Long duracao) {
		Pauta pauta = pautaService.buscar(pautaId);

		Sessao sessao = new Sessao();

		OffsetDateTime agora = OffsetDateTime.now();

		sessao.setInicio(agora);
		sessao.setFim(duracao != null ? agora.plus(Duration.ofMillis(duracao)) : agora.plusMinutes(1));
		sessao.setPauta(pauta);

		return sessaoRepository.save(sessao);
	}

	@Transactional
	public Voto votar(Long sessaoId, String cpf, boolean voto) {
		Sessao sessao = buscar(sessaoId);

		StatusUsuario status = cpfVerificador.getStatus(cpf);

		if (StatusUsuario.UNABLE_TO_VOTE == status) {
			throw new NegocioException("cpf inapto a votacao");
		}

		return sessao.votar(cpf, voto);
	}

}
