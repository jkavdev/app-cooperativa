package br.com.jkavdev.cooperativa.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoModel {

	private Long id;

	private PautaIdModel pauta;

	private OffsetDateTime inicio;

	private OffsetDateTime fim;

}
