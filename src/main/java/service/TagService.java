package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.WebUtil;
import dao.TagDAO;
import model.Tag;

public class TagService extends Service<TagDAO> {

	public TagService() {
		super(new TagDAO(), "indexTag.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/tags");
		return html;
	}

	public Object getLerTag(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/tag");
		html = html.replaceFirst("~FBODY~", "{\"idTag\":\"" + request.params(":idTag") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarTags());
	}

	public Object listarTagsPorJogo(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idJogo = parent.path("idJogo").asLong();
			return WebUtil.jsonLista(dao.listarTagsDeJogo(idJogo));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postLerTag(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idTag = parent.path("idTag").asLong();
			if (idTag == 0L)
				return WebUtil.jsonLista(dao.listarTags());
			return WebUtil.jsonPadrao(dao.lerTag(idTag));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarTag(Request request, Response response) {
		response.type("application/json");
		try {
			Tag t = objectMapper.readValue(request.body(), Tag.class);
			return WebUtil.jsonPadrao(dao.inserirTag(t) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarTag(Request request, Response response) {
		response.type("application/json");
		try {
			Tag t = objectMapper.readValue(request.body(), Tag.class);
			return WebUtil.jsonPadrao(dao.atualizarTag(t) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarTag(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idTag = parent.path("idTag").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirTag(idTag) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
