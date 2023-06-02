package service;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.SteamWebApiRequest;

import spark.Request;
import spark.Response;
import util.WebUtil;

public class SteamSyncService {
	private SteamWebApiClient client;
	private SteamWebApiRequest req;

	public SteamSyncService() {
		client = new SteamWebApiClient.SteamWebApiClientBuilder(System.getenv("STEAM_API_KEY")).build();
		req = new GetOwnedGamesRequest.GetOwnedGamesRequestBuilder("steamId").includePlayedFreeGames(true).buildRequest();
	}

	public Object postSincronizar(Request request, Response response) {
		try {
			var x = client.processRequest(req); // TODO:
		} catch (SteamApiException e) {
			return WebUtil.jsonPadrao("ERRO AO CONTATAR STEAM");
		}
		return null;
	}
}
