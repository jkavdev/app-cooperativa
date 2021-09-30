package br.com.jkavdev.cooperativa.mensageria;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jkavdev.cooperativa.dominio.dto.ResumoSessao;
import br.com.jkavdev.cooperativa.dominio.repositorio.ISessaoRepository;

@Service
@EnableScheduling
public class SessaoVerificadorService {

	private Logger logger = LoggerFactory.getLogger(SessaoVerificadorService.class);

	@Autowired
	private ISessaoRepository sessaoRepository;

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Transactional
	@Scheduled(fixedRate = 10000L)
	public void verificarSessoes() {
		List<ResumoSessao> sessoes = this.sessaoRepository.sessoesEncerradas();

		if (sessoes.isEmpty()) {
			return;
		}

		logger.info(sessoes.toString());

		sessoes.stream().forEach(s -> amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,
				RabbitMQConfig.ROUNTING_KEY, s.toString()));

		sessaoRepository.encerrarSessoes(sessoes.stream().map(ResumoSessao::getId).collect(Collectors.toList()));
	}

}
