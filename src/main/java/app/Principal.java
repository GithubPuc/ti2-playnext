package app;

import static spark.Spark.*;

import service.InteresseService;
import service.JogoService;
import service.RecomendacaoService;
import service.TagRelService;
import service.TagService;
import service.UsuarioService;

public class Principal {

	public static void main(String[] args) {
		JogoService jogoService = new JogoService();
		UsuarioService usuarioService = new UsuarioService();
		TagService tagService = new TagService();
		TagRelService tagRelService = new TagRelService();
		InteresseService interesseService = new InteresseService();
		RecomendacaoService recomendacaoService = new RecomendacaoService();

		port(4500);

		staticFiles.location("/public");

		get("/", (request, response) -> {
			response.redirect("/jogos");
			return "";
		});

		// Jogos
		post("/jogos", jogoService::postListar);
		get("/jogos", jogoService::getListar);

		post("/jogo", jogoService::postLerJogo);
		get("/jogo/:idJogo", jogoService::getLerJogo);

		post("/jogo/create", jogoService::postCriarJogo);
		post("/jogo/update", jogoService::postAtualizarJogo);
		post("/jogo/delete", jogoService::postDeletarJogo);

		// Usuarios
		post("/usuarios", usuarioService::postListar);
		get("/usuarios", usuarioService::getListar);

		post("/usuario", usuarioService::postLerUsuario);
		get("/usuario/:idUsuario", usuarioService::getLerUsuario);

		post("/usuario/create", usuarioService::postCriarUsuario);
		post("/usuario/update", usuarioService::postAtualizarUsuario);
		post("/usuario/delete", usuarioService::postDeletarUsuario);

		// Tags
		post("/tags", tagService::postListar);
		get("/tags", tagService::getListar);

		post("/tags/jogo", tagService::listarTagsPorJogo);

		post("/tag", tagService::postLerTag);
		get("/tag/:idTag", tagService::getLerTag);

		post("/tag/create", tagService::postCriarTag);
		post("/tag/update", tagService::postAtualizarTag);
		post("/tag/delete", tagService::postDeletarTag);

		// TagRels
		post("/tagrels", tagRelService::postListar);
		get("/tagrels", tagRelService::getListar);

		post("/tagrel", tagRelService::postLerTagRel);
		get("/tagrel/:idJogo/:idTag/", tagRelService::getLerTagRel);

		post("/tagrel/create", tagRelService::postCriarTagRel);
		post("/tagrel/update", tagRelService::postAtualizarTagRel);
		post("/tagrel/delete", tagRelService::postDeletarTagRel);

		// Interesses
		post("/interesses", interesseService::postListar);
		get("/interesses", interesseService::getListar);

		post("/interesses/usuario", interesseService::postListarUsuario);

		post("/interesse", interesseService::postLerInteresse);
		get("/interesse/:idInteresse", interesseService::getLerInteresse);

		post("/interesse/create", interesseService::postCriarInteresse);
		post("/interesse/update", interesseService::postAtualizarInteresse);
		post("/interesse/delete", interesseService::postDeletarInteresse);

		// Recomendacao
		post("/recomendacoes", recomendacaoService::postListar);
		get("/recomendacoes", recomendacaoService::getListar);

		post("/recomendacoes/usuario", recomendacaoService::postListarUsuario);

		post("/recomendacao", recomendacaoService::postLerRecomendacao);
		get("/recomendacao/:idRecomendacao", recomendacaoService::getLerRecomendacao);

		post("/recomendacao/create", recomendacaoService::postCriarRecomendacao);
		post("/recomendacao/update", recomendacaoService::postAtualizarRecomendacao);
		post("/recomendacao/delete", recomendacaoService::postDeletarRecomendacao);
	}
}