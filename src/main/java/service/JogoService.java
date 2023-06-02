package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.WebUtil;
import dao.JogoDAO;
import model.Jogo;

public class JogoService extends Service<JogoDAO> {

	public JogoService() {
		super(new JogoDAO(), "indexJogo.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/jogos");
		return html;
	}

	public Object getLerJogo(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/jogo");
		html = html.replaceFirst("~FBODY~", "{\"idJogo\":\"" + request.params(":idJogo") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarJogos());
	}

	public Object postLerJogo(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idJogo = parent.path("idJogo").asLong();
			if (idJogo == 0L)
				return WebUtil.jsonLista(dao.listarJogos());
			return WebUtil.jsonPadrao(dao.lerJogo(idJogo));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postLerJogosPorTituloParcial(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idJogo = parent.path("titulo").asLong();
			return WebUtil.jsonPadrao(dao.lerJogo(idJogo));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postLerJogoPorTitulo(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idJogo = parent.path("titulo").asLong();
			return WebUtil.jsonPadrao(dao.lerJogo(idJogo));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postLerJogoPorSteamId(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idJogo = parent.path("steamIdJogo").asLong();
			return WebUtil.jsonPadrao(dao.lerJogo(idJogo));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarJogo(Request request, Response response) {
		response.type("application/json");
		try {
			Jogo j = objectMapper.readValue(request.body(), Jogo.class);
			return WebUtil.jsonPadrao(dao.inserirJogo(j) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarJogo(Request request, Response response) {
		response.type("application/json");
		try {
			Jogo j = objectMapper.readValue(request.body(), Jogo.class);
			return WebUtil.jsonPadrao(dao.atualizarJogo(j) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarJogo(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idJogo = parent.path("idJogo").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirJogo(idJogo) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
