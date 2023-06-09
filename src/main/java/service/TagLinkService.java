package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.WebUtil;
import dao.TagLinkDAO;
import model.TagLink;

public class TagLinkService extends Service<TagLinkDAO> {

	public TagLinkService() {
		super(new TagLinkDAO(), "indexTagLink.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/taglinks");
		return html;
	}

	public Object getLerTagLink(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/taglink");
		html = html.replaceFirst("~FBODY~",
				"{\"idJogo\":\"" + request.params(":idJogo") +
						", \"idTag\":\"" + request.params(":idTag") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarTagLinks());
	}

	public Object postLerTagLink(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idJogo = parent.path("idJogo").asLong();
			long idTag = parent.path("idTag").asLong();
			if (idTag == 0L)
				return WebUtil.jsonLista(dao.listarTagLinks());
			return WebUtil.jsonPadrao(dao.lerTagLink(idJogo, idTag));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarTagLink(Request request, Response response) {
		response.type("application/json");
		try {
			TagLink tr = objectMapper.readValue(request.body(), TagLink.class);
			return WebUtil.jsonPadrao(dao.inserirTagLink(tr) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarTagLink(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			TagLink tr = new TagLink();
			tr.setIdJogo(parent.path("idJogo").asLong());
			tr.setIdTag(parent.path("idTag").asLong());
			long novoIdTag = parent.path("newIdTag").asLong();
			return WebUtil.jsonPadrao(dao.atualizarTagLink(tr, novoIdTag) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarTagLink(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idJogo = parent.path("idJogo").asLong();
			long idTag = parent.path("idTag").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirTagLink(idJogo, idTag) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
