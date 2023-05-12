package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;

import dao.TagRelDAO;
import model.TagRel;

public class TagRelService extends Service<TagRelDAO> {
	public TagRelService() {
		super(new TagRelDAO(), "indexTagRel.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/tagrels");
		return html;
	}

	public Object getLerTagRel(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/tagrel");
		html = html.replaceFirst("~FBODY~",
				"{\"idJogo\":\"" + request.params(":idJogo") +
						", \"idTag\":\"" + request.params(":idTag") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return jsonLista(dao.listarTagRels());
	}

	public Object postLerTagRel(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idJogo = parent.path("idJogo").asLong();
			Long idTag = parent.path("idTag").asLong();
			if (idTag == 0L)
				return jsonLista(dao.listarTagRels());
			return jsonPadrao(dao.lerTagRel(idJogo, idTag));
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarTagRel(Request request, Response response) {
		response.type("application/json");
		try {
			TagRel tr = objectMapper.readValue(request.body(), TagRel.class);
			return jsonPadrao(dao.inserirTagRel(tr) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarTagRel(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			TagRel tr = new TagRel();
			tr.setIdJogo(parent.path("idJogo").asLong());
			tr.setIdTag(parent.path("idTag").asLong());
			long novoIdTag = parent.path("newIdTag").asLong();
			return jsonPadrao(dao.atualizarTagRel(tr, novoIdTag) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarTagRel(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idJogo = parent.path("idJogo").asLong();
			Long idTag = parent.path("idTag").asLong();
			return jsonPadrao(0, dao.excluirTagRel(idJogo, idTag) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
