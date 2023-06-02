package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.Seguranca;
import util.WebUtil;
import dao.PerfilDAO;
import model.Perfil;

public class PerfilService extends Service<PerfilDAO> {

	public PerfilService() {
		super(new PerfilDAO(), "indexPerfil.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/perfils");
		return html;
	}

	public Object getLerPerfil(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/perfil");
		html = html.replaceFirst("~FBODY~", "{\"idPerfil\":\"" + request.params(":idPerfil") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarPerfils());
	}

	public Object postLerPerfil(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idPerfil = parent.path("idPerfil").asLong();
			if (idPerfil == 0L)
				return WebUtil.jsonLista(dao.listarPerfils());
			return WebUtil.jsonPadrao(dao.lerPerfil(idPerfil));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarPerfil(Request request, Response response) {
		response.type("application/json");
		try {
			Perfil u = objectMapper.readValue(request.body(), Perfil.class);
			u.setSenha(Seguranca.hash(u.getSenha()));
			return WebUtil.jsonPadrao(dao.inserirPerfil(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarPerfil(Request request, Response response) {
		response.type("application/json");
		try {
			Perfil u = objectMapper.readValue(request.body(), Perfil.class);
			u.setSenha(Seguranca.hash(u.getSenha()));
			return WebUtil.jsonPadrao(dao.atualizarPerfil(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarPerfil(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idPerfil = parent.path("idPerfil").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirPerfil(idPerfil) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
