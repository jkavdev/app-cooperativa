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

@Component
public class SessaoWatcher {
	
	private Logger logger = LoggerFactory.getLogger(SessaoWatcher.class);

	private Set<String> encerradas = new HashSet<>();

	@RabbitListener(//
			bindings = @QueueBinding(//
					value = @Queue(value = RabbitMQConfig.QUEUE_NAME), //
					exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_NAME, type = ExchangeTypes.TOPIC), //
					key = RabbitMQConfig.ROUNTING_KEY))
	public void listenSessoes(String sessao) {
		logger.info(sessao);
		encerradas.add(sessao);
	}

	public Set<String> getEncerradas() {
		return encerradas;
	}

}
