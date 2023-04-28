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
            Scanner sc = new Scanner(new File("src/main/resources/internal/index.html"));
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

    public Object getIndex(Request request, Response response) {
        String html = makeIndex();
        html = html.replaceFirst("~FETCH~", "/jogos");
        return html;
    }

    public Object getJogo(Request request, Response response) {
        String html = makeIndex();
        html = html.replaceFirst("~FETCH~", "/jogo");
        html = html.replaceFirst("~FBODY~", "{\"idJogo\":\"" + request.params(":idJogo") + "\"}");
        return html;
    }

    public Object postLista(Request request, Response response) {
        response.type("application/json");
        return jsonPadrao(dao.listarJogos());
    }

    public Object postJogo(Request request, Response response) {
        response.type("application/json");
        try {
            JsonNode parent = objectMapper.readTree(request.body());
            Long idJogo = parent.path("idJogo").asLong();
            if (idJogo == 0L)
                return jsonPadrao(dao.listarJogos());
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

    public Object postAtualizaJogo(Request request, Response response) {
        response.type("application/json");
        try {
            Jogo u = objectMapper.readValue(request.body(), Jogo.class);
            return jsonPadrao(dao.atualizarJogo(u) ? "Sucesso" : "Erro interno");
        } catch (Exception e) {
            response.status(400);
            return jsonPadrao("\"BAD REQUEST\"");
        }
    }

    public Object posDeletatJogo(Request request, Response response) {
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

    private String jsonPadrao(Object[] arr) {
        String r = "[";
        for (int i = 0; i < arr.length - 1; i++)
            r += arr[i].toString() + ",";
        r += arr[arr.length - 1].toString();
        r += "]";
        return jsonPadrao(1, r);
    }

    private String jsonPadrao(Object o) {
        return jsonPadrao(0, o.toString());
    }
}
