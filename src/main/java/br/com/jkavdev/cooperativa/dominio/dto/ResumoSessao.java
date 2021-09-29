package br.com.jkavdev.cooperativa.dominio.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ResumoSessao implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private Long id;

	private String descricao;

	private OffsetDateTime inicio;

	private OffsetDateTime fim;

	private Long qtdeVotos;

	@Override
	public String toString() {
		return "Pauta " + descricao 
				+ ", iniciada as " + inicio 
				+ ", encerrando-se as " + fim 
				+ " com " + qtdeVotos + " voto(s)";
	}

}
