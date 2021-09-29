package br.com.jkavdev.cooperativa.api.controller.v2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jkavdev.cooperativa.api.model.v2.SessaoModelV2;
import br.com.jkavdev.cooperativa.dominio.modelo.Sessao;
import br.com.jkavdev.cooperativa.dominio.service.SessaoService;

@RestController
@RequestMapping("/pautas/{pautaId}/sessoes")
public class SessaoControllerV2 {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private SessaoService sessaoService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping(value = "/{sessaoId}", headers = { "Accept=application/vnd.v2+json" })
	public SessaoModelV2 resumoV2(@PathVariable Long sessaoId) {
		Sessao sessao = sessaoService.resumo(sessaoId);

		SessaoModelV2 model = mapper.map(sessao, SessaoModelV2.class);
		model.setQtdeVotos(sessao.getVotos().size());

		return model;
	}

}
