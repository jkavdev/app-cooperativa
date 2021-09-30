package br.com.jkavdev.cooperativa.api.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaInput {

	@NotBlank
	private String descricao;

}
