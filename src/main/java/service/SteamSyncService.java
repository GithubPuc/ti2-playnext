package service;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;

import dao.InteresseDAO;
import dao.PerfilDAO;
import model.Interesse;
import model.Perfil;
import spark.Request;
import spark.Response;
import util.WebUtil;

public class SteamSyncService {
	private SteamWebApiClient client;
	private PerfilDAO perfilDAO;
	private InteresseDAO interesseDAO;

	public SteamSyncService() {
		client = new SteamWebApiClient.SteamWebApiClientBuilder(System.getenv("STEAM_API_KEY")).build();
		perfilDAO = new PerfilDAO();
		interesseDAO = new InteresseDAO();
	}

	public Object postSincronizar(Request request, Response response) {
		try {
			long idUsuario = WebUtil.objectMapper.readValue("idUsuario", long.class);
			Perfil p = perfilDAO.lerPerfil(idUsuario);
			if (p == null)
			return WebUtil.jsonPadrao("Perfil não encontrado");
			if (p.getSteamId() == null)
			return WebUtil.jsonPadrao("Não há Steam vinculada ao usuario");
			SteamWebApiRequest req = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder("76561198841735636")
					.includePlayedFreeGames(true).buildRequest();
			GetOwnedGames games = client.processRequest(req);
			for (var game : games.getResponse().getGames()) {
				long steamIdJogo = game.getAppid();
				interesseDAO.inserirInteresse(new Interesse());
			}
		} catch (SteamApiException e) {
			return WebUtil.jsonPadrao("ERRO AO CONTATAR STEAM");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
