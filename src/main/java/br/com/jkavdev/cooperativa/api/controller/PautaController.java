package br.com.jkavdev.cooperativa.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jkavdev.cooperativa.api.input.PautaInput;
import br.com.jkavdev.cooperativa.dominio.repositorio.PautaRepository;
import br.com.jkavdev.cooperativa.dominio.service.PautaService;

@RestController
@RequestMapping("/pautas")
public class PautaController {

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private PautaService pautaService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public PautaModel adicionar(@RequestBody @Valid PautaInput input) {
		return mapper.map(pautaService.adicionar(input.getDescricao()), PautaModel.class);
	}

}
