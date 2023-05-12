package service;

import java.util.Scanner;
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

	protected String lerHTML(String filename) {
		Scanner sc = new Scanner(Service.class.getResourceAsStream("/internal/" + filename), "UTF-8");
		String html = "";
		while (sc.hasNext())
			html += sc.nextLine() + '\n';
		sc.close();
		return html;
	}

	protected String construirPagina() {
		if (!dao.isConectado())
			return lerHTML("Erro.html").replaceFirst("~ERRO~", "Erro de conexão com banco de dados");
		return lerHTML(index);
	}

	protected String jsonPadrao(int tipo, String valor) {
		return String.format("{\"tipo\":%d,\"valor\":%s}", tipo, valor);
	}

	protected String jsonLista(Object[] o) {
		try {
			return jsonPadrao(1, objectMapper.writeValueAsString(o));
		} catch (Exception e) {
			return jsonPadrao(0, "Erro interno");
		}
	}

	protected String jsonPadrao(Object o) {
		try {
			return jsonPadrao(0, objectMapper.writeValueAsString(o));
		} catch (Exception e) {
			return jsonPadrao(0, "Erro interno");
		}
	}
}
