package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.Seguranca;
import util.WebUtil;
import dao.EditarDAO;
import model.Editar;

public class EditarService extends Service<EditarDAO> {

	public EditarService() {
		super(new EditarDAO(), "indexEditar.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/editars");
		return html;
	}

	public Object getLerEditar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/editar");
		html = html.replaceFirst("~FBODY~", "{\"idEditar\":\"" + request.params(":idEditar") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarEditars());
	}

	public Object postLerEditar(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idEditar = parent.path("idEditar").asLong();
			if (idEditar == 0L)
				return WebUtil.jsonLista(dao.listarEditars());
			return WebUtil.jsonPadrao(dao.lerEditar(idEditar));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarEditar(Request request, Response response) {
		response.type("application/json");
		try {
			Editar u = objectMapper.readValue(request.body(), Editar.class);
			u.setSenha(Seguranca.hash(u.getSenha()));
			return WebUtil.jsonPadrao(dao.inserirEditar(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarEditar(Request request, Response response) {
		response.type("application/json");
		try {
			Editar u = objectMapper.readValue(request.body(), Editar.class);
			u.setSenha(Seguranca.hash(u.getSenha()));
			return WebUtil.jsonPadrao(dao.atualizarEditar(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarEditar(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idEditar = parent.path("idEditar").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirEditar(idEditar) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
