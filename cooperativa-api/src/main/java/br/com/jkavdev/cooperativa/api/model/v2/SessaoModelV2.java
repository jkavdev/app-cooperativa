package br.com.jkavdev.cooperativa.api.model.v2;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.jkavdev.cooperativa.api.model.PautaIdModel;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class SessaoModelV2 {

	private Long id;

	private PautaIdModel pauta;

	private OffsetDateTime inicio;

	private OffsetDateTime fim;
	
	private Integer qtdeVotos;

}
