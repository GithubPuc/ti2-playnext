package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.WebUtil;
import dao.HomeDAO;
import model.Home;

public class HomeService extends Service<HomeDAO> {

	public HomeService() {
		super(new HomeDAO(), "indexHome.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/homes");
		return html;
	}

	public Object getLerHome(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/home");
		html = html.replaceFirst("~FBODY~", "{\"idHome\":\"" + request.params(":idHome") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarHomes());
	}

	public Object postLerHome(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idHome = parent.path("idHome").asLong();
			if (idHome == 0L)
				return WebUtil.jsonLista(dao.listarHomes());
			return WebUtil.jsonPadrao(dao.lerHome(idHome));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarHome(Request request, Response response) {
		response.type("application/json");
		try {
			Home j = objectMapper.readValue(request.body(), Home.class);
			return WebUtil.jsonPadrao(dao.inserirHome(j) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarHome(Request request, Response response) {
		response.type("application/json");
		try {
			Home j = objectMapper.readValue(request.body(), Home.class);
			return WebUtil.jsonPadrao(dao.atualizarHome(j) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarHome(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idHome = parent.path("idHome").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirHome(idHome) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
