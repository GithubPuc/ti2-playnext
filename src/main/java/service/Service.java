package service;

import util.WebUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAO;

public class Service<D extends DAO> {
	protected final ObjectMapper objectMapper = new ObjectMapper();
	protected D dao;
	protected String index;

	public Service(D dao, String index) {
		this.dao = dao;
		this.index = index;
		this.dao.conectar();
	}

	protected String construirPagina() {
		if (!dao.isConectado())
			return WebUtil.lerHTML("Erro.html").replaceFirst("~ERRO~", "Erro de conexão com banco de dados");
		return WebUtil.lerHTML(index);
	}
}
