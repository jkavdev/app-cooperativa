package br.com.jkavdev.cooperativa.mensageria;

import java.util.HashSet;
import java.util.Set;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SessaoWatcher {

	private Set<String> encerradas = new HashSet<>();

	@RabbitListener(//
			bindings = @QueueBinding(//
					value = @Queue(value = RabbitMQConfig.QUEUE_NAME), //
					exchange = @Exchange(value = RabbitMQConfig.EXCHANGE_NAME, type = ExchangeTypes.TOPIC), //
					key = RabbitMQConfig.ROUNTING_KEY))
	public void listenSessoes(String sessao) {
		encerradas.add(sessao);
	}

	public Set<String> getEncerradas() {
		return encerradas;
	}

}
