package br.com.jkavdev.cooperativa.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ErroRequisicao {

	private int status;

	private String titulo;

	private OffsetDateTime dataHora;

	private List<Campo> campos;

	public ErroRequisicao() {
		this.dataHora = OffsetDateTime.now();
	}

	public ErroRequisicao(int status, String titulo) {
		this();
		this.status = status;
		this.titulo = titulo;
	}

	public ErroRequisicao(int status, String titulo, List<Campo> campos) {
		this(status, titulo);
		this.campos = campos;
	}

	@AllArgsConstructor
	@Getter
	public static class Campo {
		private String nome;
		private String mensagem;
	}

}
