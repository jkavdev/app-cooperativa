package br.com.jkavdev.cooperativa.mensageria;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.jkavdev.cooperativa.dominio.dto.SessaoModel;

@Component
public class SessaoWatcher {

	private Logger logger = LoggerFactory.getLogger(SessaoWatcher.class);

	private Set<SessaoModel> encerradas = new HashSet<>();

	@RabbitListener(//
			bindings = @QueueBinding(//
					value = @Queue, //
					exchange = @Exchange(value = "sessoes", type = ExchangeTypes.TOPIC), //
					key = "*"))
	public void listenSessoes(SessaoModel sessao) {
		logger.info(sessao.toString());
		encerradas.add(sessao);
	}

	public Set<SessaoModel> getEncerradas() {
		return encerradas;
	}

}
