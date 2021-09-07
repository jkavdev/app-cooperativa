package br.com.jkavdev.cooperativa.dominio.modelo;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sessao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Pauta pauta;

	private OffsetDateTime inicio;

	private Long finalizacaoEm = 1000L;

	@OneToMany(mappedBy = "sessao")
	private List<Voto> votos = new ArrayList<>();

}
