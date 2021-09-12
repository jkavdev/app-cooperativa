package br.com.jkavdev.cooperativa.dominio.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;

	private OffsetDateTime inicio;

	private OffsetDateTime fim;

	private Long qtdeVotos;

	@Override
	public String toString() {
		return "Pauta " + descricao 
				+ ", iniciada as " + inicio 
				+ ", encerrada as " + fim 
				+ " com " + qtdeVotos + " votos";
	}

}
