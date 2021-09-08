package br.com.jkavdev.cooperativa.externo;

public enum StatusUsuario {
	ABLE_TO_VOTE("Apto a votar"), UNABLE_TO_VOTE("Inapto a votar");

	private String descricao;

	StatusUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
