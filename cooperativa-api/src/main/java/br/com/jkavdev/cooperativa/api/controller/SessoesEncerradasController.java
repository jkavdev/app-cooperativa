package br.com.jkavdev.cooperativa.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jkavdev.cooperativa.dominio.dto.ResumoSessao;
import br.com.jkavdev.cooperativa.dominio.repositorio.SessaoRepository;

@RestController
@RequestMapping("/sessoes/encerradas")
public class SessoesEncerradasController {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private SessaoRepository sessaoRepository;

	@GetMapping
	public List<ResumoSessao> sessoes() {
		return sessaoRepository.sessoesEncerradas();
	}

}
