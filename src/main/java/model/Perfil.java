package model;

public class Perfil {
	private long idUsuario;
	private String username;
	private String steamId;

	public Perfil(long idUsuario, String username, String steamId) {
		this.idUsuario = idUsuario;
		this.username = username;
		this.steamId = steamId;
	}

	public Perfil() {
		this(-1, null, null);
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSteamId() {
		return steamId;
	}

	public void setSteamId(String steamId) {
		this.steamId = steamId;
	}
}
