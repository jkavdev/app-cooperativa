package br.com.jkavdev.cooperativa.externo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.jkavdev.cooperativa.dominio.exception.NegocioException;

@Component
public class CpfVerificador {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${verificador-cpf-url}")
	private String urlVerificador;

	public StatusUsuario getStatus(String cpf) {
		try {
			String url = urlVerificador + cpf;
			StatusModel resp = Optional.ofNullable(restTemplate.getForObject(url, StatusModel.class))
					.orElseThrow(() -> new NegocioException("erro ao consultar cpf"));

			return resp.getStatus();
		} catch (HttpClientErrorException e) {
			if (404 == e.getRawStatusCode()) {
				throw new NegocioException("cpf invalido");
			}
		} catch (Exception e) {
			throw new NegocioException("erro ao consultar cpf, " + e.getMessage());
		}
		return null;
	}

}
