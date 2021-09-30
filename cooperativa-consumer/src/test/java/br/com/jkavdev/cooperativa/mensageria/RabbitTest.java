package br.com.jkavdev.cooperativa.mensageria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
@ContextConfiguration
public class RabbitTest {

	@Container
	static RabbitMQContainer container = new RabbitMQContainer(
			DockerImageName.parse("rabbitmq").withTag("3.7.25-management-alpine"));

	@DynamicPropertySource
	static void configure(DynamicPropertyRegistry registry) {
		registry.add("spring.rabbitmq.host", container::getContainerIpAddress);
		registry.add("spring.rabbitmq.port", container::getAmqpPort);
	}

	@Autowired
	SessaoWatcher sessaoWatcher;

//	@Test verificar como montar esse teste, depois que o consumidor foi separado do produtor
	void rabbitTest() throws InterruptedException {
		Thread.sleep(12000);

		assertEquals(1, this.sessaoWatcher.getEncerradas().size());
	}
}
