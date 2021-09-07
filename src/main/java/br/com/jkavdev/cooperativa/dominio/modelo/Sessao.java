package br.com.jkavdev.cooperativa.dominio.modelo;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.jkavdev.cooperativa.dominio.exception.NegocioException;
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

	private OffsetDateTime fim;

	@OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL)
	private List<Voto> votos = new ArrayList<>();

	public Voto realizarVoto(String cpf, boolean voto) {
		boolean sessaoEncerrada = OffsetDateTime.now().isAfter(getFim());
		if (sessaoEncerrada) {
			throw new NegocioException("sessao encerrada");
		}

		boolean jaVotou = getVotos().stream().map(v -> v.getCpf()).anyMatch(cpfSalvo -> cpfSalvo.equals(cpf));
		if (jaVotou) {
			throw new NegocioException("voto ja computado");
		}

		Voto votacao = new Voto();
		votacao.setCpf(cpf);
		votacao.setSessao(this);
		getVotos().add(votacao);
		return votacao;
	}

}
