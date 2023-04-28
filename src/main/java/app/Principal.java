package app;

import static spark.Spark.*;

import service.JogoService;

public class Principal {

	private static JogoService jogoService = new JogoService();

	public static void main(String[] args) {
		port(4500);

		// staticFiles.location("/public");

		get("/", (request, response) -> {
			response.redirect("/jogos");
			return "";
		});

		post("/jogos", (request, response) -> jogoService.postLista(request, response));
		get("/jogos", (request, response) -> jogoService.getIndex(request, response));

		post("/jogo", (request, response) -> jogoService.postJogo(request, response));
		get("/jogo/:idJogo", (request, response) -> jogoService.getJogo(request, response));

		post("/jogo/create", (request, response) -> jogoService.postCriarJogo(request, response));
		post("/jogo/update", (request, response) -> jogoService.postAtualizaJogo(request, response));
		post("/jogo/delete", (request, response) -> jogoService.posDeletatJogo(request, response));
	}
}