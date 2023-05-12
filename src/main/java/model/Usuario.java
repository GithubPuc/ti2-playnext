package model;

public class Usuario {
	private long idUsuario;
	private String username;
	private String email;
	private String senha;
	private int grupo;

	public Usuario(long idUsuario, String username, String email, String senha, int grupo) {
		this.idUsuario = idUsuario;
		this.username = username;
		this.email = email;
		this.senha = senha;
		this.grupo = grupo;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
}
