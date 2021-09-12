package br.com.jkavdev.cooperativa.mensageria;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.jkavdev.cooperativa.dominio.dto.SessaoModel;
import br.com.jkavdev.cooperativa.dominio.repositorio.SessaoRepository;

@Service
@EnableScheduling
public class SessaoVerificadorService {

	@Autowired
	private SessaoRepository sessaoRepository;

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Scheduled(fixedRate = 15000L)
	public void verificarSessoes() {
		List<SessaoModel> sessoes = this.sessaoRepository.sessoesEncerradas();
		sessoes.stream().forEach(s -> {
			amqpTemplate.convertAndSend("sessoes", s.getDescricao(), s);
		});
	}

}
