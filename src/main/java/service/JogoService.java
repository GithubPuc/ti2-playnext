package service;

import com.fasterxml.jackson.databind.JsonNode;

import dao.JogoDAO;
import model.Jogo;
import spark.Request;
import spark.Response;

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
		return jsonLista(dao.listarJogos());
	}

	public Object postLerJogo(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idJogo = parent.path("idJogo").asLong();
			if (idJogo == 0L)
				return jsonLista(dao.listarJogos());
			return jsonPadrao(dao.lerJogo(idJogo));
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarJogo(Request request, Response response) {
		response.type("application/json");
		try {
			Jogo u = objectMapper.readValue(request.body(), Jogo.class);
			return jsonPadrao(dao.inserirJogo(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarJogo(Request request, Response response) {
		response.type("application/json");
		try {
			Jogo u = objectMapper.readValue(request.body(), Jogo.class);
			return jsonPadrao(dao.atualizarJogo(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarJogo(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idJogo = parent.path("idJogo").asLong();
			return jsonPadrao(0, dao.excluirJogo(idJogo) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
