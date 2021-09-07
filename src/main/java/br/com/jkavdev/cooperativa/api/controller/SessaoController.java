package br.com.jkavdev.cooperativa.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jkavdev.cooperativa.api.model.SessaoModel;
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
	public SessaoModel abrir(@PathVariable Long pautaId) {
		return mapper.map(sessaoService.abrir(pautaId, pautaId), SessaoModel.class);
	}

}
