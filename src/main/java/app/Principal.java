package app;

import static spark.Spark.*;

import service.JogoService;
import service.UsuarioService;

public class Principal {

	private static JogoService jogoService = new JogoService();
	private static UsuarioService usuarioService = new UsuarioService();

	public static void main(String[] args) {
		port(4500);

		// staticFiles.location("/public");

		get("/", (request, response) -> {
			response.redirect("/jogos");
			return "";
		});

		post("/jogos", (request, response) -> jogoService.postListar(request, response));
		get("/jogos", (request, response) -> jogoService.getListar(request, response));

		post("/jogo", (request, response) -> jogoService.postLerJogo(request, response));
		get("/jogo/:idJogo", (request, response) -> jogoService.getLerJogo(request, response));

		post("/jogo/create", (request, response) -> jogoService.postCriarJogo(request, response));
		post("/jogo/update", (request, response) -> jogoService.postAtualizarJogo(request, response));
		post("/jogo/delete", (request, response) -> jogoService.postDeletarJogo(request, response));

		post("/usuarios", (request, response) -> usuarioService.postListar(request, response));
		get("/usuarios", (request, response) -> usuarioService.getListar(request, response));

		post("/usuario", (request, response) -> usuarioService.postLerUsuario(request, response));
		get("/usuario/:idUsuario", (request, response) -> usuarioService.getLerUsuario(request, response));

		post("/usuario/create", (request, response) -> usuarioService.postCriarUsuario(request, response));
		post("/usuario/update", (request, response) -> usuarioService.postAtualizarUsuario(request, response));
		post("/usuario/delete", (request, response) -> usuarioService.postDeletarUsuario(request, response));
	}
}