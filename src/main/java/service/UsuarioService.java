package service;

import java.io.File;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;

public class UsuarioService {
	private final ObjectMapper objectMapper = new ObjectMapper();
	private UsuarioDAO dao = new UsuarioDAO();
	private boolean statusDAO;

	public UsuarioService() {
		statusDAO = dao.conectar();
	}

	private String makeIndex() {
		String html = "";
		try {
			Scanner sc = new Scanner(new File("src/main/resources/internal/indexJogo.html"), "UTF-8");
			while (sc.hasNext()) {
				html += sc.nextLine() + '\n';
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!statusDAO)
			html = html.replaceFirst("~ERRO~", "Erro de conexão com banco de dados");
		return html;
	}

	public Object getListar(Request request, Response response) {
		String html = makeIndex();
		html = html.replaceFirst("~FETCH~", "/usuarios");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return jsonLista(dao.listarUsuarios());
	}

	public Object getLerUsuario(Request request, Response response) {
		String html = makeIndex();
		html = html.replaceFirst("~FETCH~", "/Usuario");
		html = html.replaceFirst("~FBODY~", "{\"idUsuario\":\"" + request.params(":idUsuario") + "\"}");
		return html;
	}

	public Object postLerUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idUsuario = parent.path("idUsuario").asLong();
			if (idUsuario == 0L)
				return jsonLista(dao.listarUsuarios());
			return jsonPadrao(dao.lerUsuario(idUsuario));
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			Usuario u = objectMapper.readValue(request.body(), Usuario.class);
			return jsonPadrao(dao.inserirUsuario(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			Usuario u = objectMapper.readValue(request.body(), Usuario.class);
			return jsonPadrao(dao.atualizarUsuario(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarUsuario(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			Long idUsuario = parent.path("idUsuario").asLong();
			return jsonPadrao(0, dao.excluirUsuario(idUsuario) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return jsonPadrao("\"BAD REQUEST\"");
		}
	}

	private String jsonPadrao(int tipo, String valor) {
		return String.format("{\"tipo\":%d,\"valor\":%s}", tipo, valor);
	}

	private String jsonLista(Object[] o) {
		try {
			return jsonPadrao(1, objectMapper.writeValueAsString(o));
		} catch (Exception e) {
			return jsonPadrao(0, "Erro interno");
		}
	}

	private String jsonPadrao(Object o) {
		try {
			return jsonPadrao(0, objectMapper.writeValueAsString(o));
		} catch (Exception e) {
			return jsonPadrao(0, "Erro Interno");
		}
	}
}
