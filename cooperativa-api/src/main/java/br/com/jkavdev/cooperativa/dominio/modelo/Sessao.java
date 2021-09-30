package br.com.jkavdev.cooperativa.dominio.modelo;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.com.jkavdev.cooperativa.dominio.exception.NegocioException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Sessao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Pauta pauta;

	private OffsetDateTime inicio;

	private OffsetDateTime fim;

	@OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL)
	private List<Voto> votos = new ArrayList<>();
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private SessaoStatus status;

	public Voto votar(String cpf, boolean voto) {
		if (isSessaoEncerrada()) {
			throw new NegocioException("sessao encerrada");
		}

		boolean jaVotou = getVotos().stream().map(v -> v.getCpf()).anyMatch(cpfSalvo -> cpfSalvo.equals(cpf));
		if (jaVotou) {
			throw new NegocioException("voto ja computado");
		}

		Voto votacao = new Voto();
		votacao.setCpf(cpf);
		votacao.setSessao(this);
		votacao.setVoto(voto);
		getVotos().add(votacao);
		return votacao;
	}

	@Transient
	public boolean isSessaoEncerrada() {
		return OffsetDateTime.now().isAfter(getFim());
	}

}
