package br.com.jkavdev.cooperativa.api.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class SessaoModel {

	private Long id;

	private PautaIdModel pauta;

	private OffsetDateTime inicio;

	private OffsetDateTime fim;
	
	private VotoModel votos;

}
