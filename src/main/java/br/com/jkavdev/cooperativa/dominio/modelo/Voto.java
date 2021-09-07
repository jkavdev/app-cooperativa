package br.com.jkavdev.cooperativa.dominio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Voto {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean voto;
	
	private String cpf;
	
	private Sessao sessao;

}
