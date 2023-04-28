package service;

import java.io.File;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.JogoDAO;
import model.Jogo;
import spark.Request;
import spark.Response;

public class JogoService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private JogoDAO dao = new JogoDAO();
    private boolean statusDAO;

    public JogoService() {
        statusDAO = dao.conectar();
    }

    private String makeIndex() {
        String html = "";
        try {
            Scanner sc = new Scanner(new File("src/main/resources/internal/indexJogo.html"));
            while (sc.hasNext()) {
                html += sc.nextLine() + '\n';
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        html = html.replaceFirst("~ERRO~", statusDAO ? "\"\"" : "Erro de conexão com banco de dados");
        return html;
    }

    public Object getListar(Request request, Response response) {
        String html = makeIndex();
        html = html.replaceFirst("~FETCH~", "/jogos");
        return html;
    }

    public Object getLerJogo(Request request, Response response) {
        String html = makeIndex();
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
            return jsonPadrao(0, "Erro interno");
        }
    }
}
