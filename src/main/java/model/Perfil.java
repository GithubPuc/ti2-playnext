package model;

// TODO: Criar perfil de Perfil com "imagem de perfil" e "opções de display", etc.

public class Perfil {
	private long idPerfil;
	private String username; //TODO: Mover campo "username" para perfil.
	private String email;
	private byte[] senha;
	private int tipo;

	public Perfil(long idPerfil, String username, String email, byte[] senha, int tipo) {
		this.idPerfil = idPerfil;
		this.username = username;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}

	public Perfil() {
		this(-1, null, null, null, -1);
	}

	public long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getSenha() {
		return senha;
	}

	public void setSenha(byte[] senha) {
		this.senha = senha;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
