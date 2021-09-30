package br.com.jkavdev.cooperativa.api.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoModel {

	private Integer qtdeVotos;

	private Map<Boolean, Long> votos = new HashMap<>();

}
