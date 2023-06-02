package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.WebUtil;
import dao.RecomendacaoDAO;
import model.Recomendacao;

public class RecomendacaoService extends Service<RecomendacaoDAO> {

	public RecomendacaoService() {
		super(new RecomendacaoDAO(), "indexRecomendacao.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/recomendacoes");
		return html;
	}

	public Object getLerRecomendacao(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/recomendacao");
		html = html.replaceFirst("~FBODY~", "{\"idRecomendacao\":\"" + request.params(":idRecomendacao") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarRecomendacoes());
	}

	public Object postListarUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idRecomendacao = parent.path("idUsuario").asLong();
			return WebUtil.jsonPadrao(dao.listarRecomendacoesParaUsuario(idRecomendacao));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postLerRecomendacao(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idRecomendacao = parent.path("idRecomendacao").asLong();
			if (idRecomendacao == 0L)
				return WebUtil.jsonLista(dao.listarRecomendacoes());
			return WebUtil.jsonPadrao(dao.lerRecomendacao(idRecomendacao));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarRecomendacao(Request request, Response response) {
		response.type("application/json");
		try {
			Recomendacao in = objectMapper.readValue(request.body(), Recomendacao.class);
			return WebUtil.jsonPadrao(dao.inserirRecomendacao(in) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarRecomendacao(Request request, Response response) {
		response.type("application/json");
		try {
			Recomendacao in = objectMapper.readValue(request.body(), Recomendacao.class);
			return WebUtil.jsonPadrao(dao.atualizarRecomendacao(in) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarRecomendacao(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idRecomendacao = parent.path("idRecomendacao").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirRecomendacao(idRecomendacao) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
