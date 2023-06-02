package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.WebUtil;
import dao.InteresseDAO;
import model.Interesse;

public class InteresseService extends Service<InteresseDAO> {

	public InteresseService() {
		super(new InteresseDAO(), "indexInteresse.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/interesses");
		return html;
	}

	public Object getLerInteresse(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/interesse");
		html = html.replaceFirst("~FBODY~", "{\"idInteresse\":\"" + request.params(":idInteresse") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarInteresses());
	}

	public Object postListarUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idInteresse = parent.path("idUsuario").asLong();
			return WebUtil.jsonLista(dao.listarInteressesDoUsuario(idInteresse));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postLerInteresse(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idInteresse = parent.path("idInteresse").asLong();
			if (idInteresse == 0L)
				return WebUtil.jsonLista(dao.listarInteresses());
			return WebUtil.jsonPadrao(dao.lerInteresse(idInteresse));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarInteresse(Request request, Response response) {
		response.type("application/json");
		try {
			Interesse in = objectMapper.readValue(request.body(), Interesse.class);
			return WebUtil.jsonPadrao(dao.inserirInteresse(in) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarInteresse(Request request, Response response) {
		response.type("application/json");
		try {
			Interesse in = objectMapper.readValue(request.body(), Interesse.class);
			return WebUtil.jsonPadrao(dao.atualizarInteresse(in) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarInteresse(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idInteresse = parent.path("idInteresse").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirInteresse(idInteresse) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
