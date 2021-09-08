package br.com.jkavdev.cooperativa.api.controller;

import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jkavdev.cooperativa.api.input.SessaoInput;
import br.com.jkavdev.cooperativa.api.input.VotoInput;
import br.com.jkavdev.cooperativa.api.model.SessaoModel;
import br.com.jkavdev.cooperativa.api.model.VotoModel;
import br.com.jkavdev.cooperativa.dominio.modelo.Sessao;
import br.com.jkavdev.cooperativa.dominio.modelo.Voto;
import br.com.jkavdev.cooperativa.dominio.service.SessaoService;

@RestController
@RequestMapping("/pautas/{pautaId}/sessoes")
public class SessaoController {

	@Autowired
	private SessaoService sessaoService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SessaoModel abrir(@PathVariable Long pautaId, @RequestBody SessaoInput input) {
		return mapper.map(sessaoService.abrir(pautaId, input.getDuracao()), SessaoModel.class);
	}

	@PutMapping("/{sessaoId}/votar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long sessaoId, @RequestBody @Valid VotoInput input) {
		sessaoService.votar(sessaoId, input.getCpf(), input.getVoto());
	}

	@GetMapping("/{sessaoId}")
	public SessaoModel resumo(@PathVariable Long sessaoId) {
		Sessao sessao = sessaoService.buscar(sessaoId);

		SessaoModel model = mapper.map(sessao, SessaoModel.class);
		Map<Boolean, Long> votos = sessao.getVotos().stream()
				.collect(Collectors.groupingBy(Voto::getVoto, Collectors.counting()));

		VotoModel voto = new VotoModel();
		voto.setQtdeVotos(sessao.getVotos().size());
		voto.setVotos(votos);

		model.setVotos(voto);

		return model;
	}

}