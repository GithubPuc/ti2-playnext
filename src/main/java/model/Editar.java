package model;

// TODO: Criar editar de Editar com "imagem de editar" e "opções de display", etc.

public class Editar {
	private long idEditar;
	private String username; //TODO: Mover campo "username" para editar.
	private String email;
	private byte[] senha;
	private int tipo;

	public Editar(long idEditar, String username, String email, byte[] senha, int tipo) {
		this.idEditar = idEditar;
		this.username = username;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}

	public Editar() {
		this(-1, null, null, null, -1);
	}

	public long getIdEditar() {
		return idEditar;
	}

	public void setIdEditar(long idEditar) {
		this.idEditar = idEditar;
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
