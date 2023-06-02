package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.WebUtil;
import dao.PerfilDAO;
import model.Perfil;

public class PerfilService extends Service<PerfilDAO> {

	public PerfilService() {
		super(new PerfilDAO(), "indexPerfil.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/perfis");
		return html;
	}

	public Object getLerPerfil(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/perfil");
		html = html.replaceFirst("~FBODY~", "{\"idUsuario\":\"" + request.params(":idUsuario") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarPerfis());
	}

	public Object postLerPerfil(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idUsuario = parent.path("idUsuario").asLong();
			if (idUsuario == 0L)
				return WebUtil.jsonLista(dao.listarPerfis());
			return WebUtil.jsonPadrao(dao.lerPerfil(idUsuario));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarPerfil(Request request, Response response) {
		response.type("application/json");
		try {
			Perfil p = objectMapper.readValue(request.body(), Perfil.class);
			return WebUtil.jsonPadrao(dao.inserirPerfil(p) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarPerfil(Request request, Response response) {
		response.type("application/json");
		try {
			Perfil p = objectMapper.readValue(request.body(), Perfil.class);
			return WebUtil.jsonPadrao(dao.atualizarPerfil(p) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarPerfil(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idUsuario = parent.path("idUsuario").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirPerfil(idUsuario) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
