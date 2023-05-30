package model;

// TODO: Criar perfil de Usuario com "imagem de perfil" e "opções de display", etc.

public class Usuario {
	private long idUsuario;
	private String username; //TODO: Mover campo "username" para perfil.
	private String email;
	private byte[] senha;
	private int tipo;

	public Usuario(long idUsuario, String username, String email, byte[] senha, int tipo) {
		this.idUsuario = idUsuario;
		this.username = username;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}

	public Usuario() {
		this(-1, null, null, null, -1);
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
