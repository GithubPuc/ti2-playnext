package model;

public class Usuario {
	private long idUsuario;
	private String email;
	private byte[] senha;
	private int tipo;

	public Usuario(long idUsuario, String email, byte[] senha, int tipo) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}

	public Usuario() {
		this(-1, null, null, -1);
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
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
