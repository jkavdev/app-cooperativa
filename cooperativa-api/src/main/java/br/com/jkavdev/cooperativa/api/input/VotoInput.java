package br.com.jkavdev.cooperativa.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoInput {

	@NotNull
	private Boolean voto;

	@NotBlank
	@CPF
	private String cpf;

}
