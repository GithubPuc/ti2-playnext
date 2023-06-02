package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.Seguranca;
import util.WebUtil;
import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService extends Service<UsuarioDAO> {

	public UsuarioService() {
		super(new UsuarioDAO(), "indexUsuario.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/usuarios");
		return html;
	}

	public Object getLerUsuario(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/usuario");
		html = html.replaceFirst("~FBODY~", "{\"idUsuario\":\"" + request.params(":idUsuario") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarUsuarios());
	}

	public Object postLerUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idUsuario = parent.path("idUsuario").asLong();
			if (idUsuario == 0L)
				return WebUtil.jsonLista(dao.listarUsuarios());
			return WebUtil.jsonPadrao(dao.lerUsuario(idUsuario));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			Usuario u = objectMapper.readValue(request.body(), Usuario.class);
			u.setSenha(Seguranca.hash(u.getSenha()));
			return WebUtil.jsonPadrao(dao.inserirUsuario(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			Usuario u = objectMapper.readValue(request.body(), Usuario.class);
			u.setSenha(Seguranca.hash(u.getSenha()));
			return WebUtil.jsonPadrao(dao.atualizarUsuario(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idUsuario = parent.path("idUsuario").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirUsuario(idUsuario) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
