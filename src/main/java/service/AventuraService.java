package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.WebUtil;
import dao.AventuraDAO;
import model.Aventura;

public class AventuraService extends Service<AventuraDAO> {

	public AventuraService() {
		super(new AventuraDAO(), "indexAventura.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/aventuras");
		return html;
	}

	public Object getLerAventura(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/aventura");
		html = html.replaceFirst("~FBODY~", "{\"idAventura\":\"" + request.params(":idAventura") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarAventuras());
	}

	public Object postListarUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idAventura = parent.path("idUsuario").asLong();
			return WebUtil.jsonPadrao(dao.listarAventurasParaUsuario(idAventura));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postLerAventura(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idAventura = parent.path("idAventura").asLong();
			if (idAventura == 0L)
				return WebUtil.jsonLista(dao.listarAventuras());
			return WebUtil.jsonPadrao(dao.lerAventura(idAventura));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarAventura(Request request, Response response) {
		response.type("application/json");
		try {
			Aventura in = objectMapper.readValue(request.body(), Aventura.class);
			return WebUtil.jsonPadrao(dao.inserirAventura(in) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarAventura(Request request, Response response) {
		response.type("application/json");
		try {
			Aventura in = objectMapper.readValue(request.body(), Aventura.class);
			return WebUtil.jsonPadrao(dao.atualizarAventura(in) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarAventura(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idAventura = parent.path("idAventura").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirAventura(idAventura) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
