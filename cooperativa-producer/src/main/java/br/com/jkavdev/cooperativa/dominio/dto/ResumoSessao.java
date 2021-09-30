package br.com.jkavdev.cooperativa.dominio.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

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
	
	public ResumoSessao(Long id, String descricao, Date inicio, Date fim, Long qtdeVotos) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.inicio = toOffsetDateTime(inicio);
		this.fim = toOffsetDateTime(fim);
		this.qtdeVotos = qtdeVotos;
	}
	
	private OffsetDateTime toOffsetDateTime(Date date) {
		return OffsetDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()),ZoneId.systemDefault());
	}

	@Override
	public String toString() {
		return "Pauta " + descricao 
				+ ", iniciada as " + inicio 
				+ ", encerrando-se as " + fim 
				+ " com " + qtdeVotos + " voto(s)";
	}

}
