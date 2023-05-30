package model;

public class Interesse {
	private long idInteresse;
	private long idUsuario;
	private long idJogo;
	private int tipo;
	private int peso;

	public Interesse(long idInteresse, long idUsuario, long idJogo, int tipo, int peso) {
		this.idInteresse = idInteresse;
		this.idUsuario = idUsuario;
		this.idJogo = idJogo;
		this.tipo = tipo;
		this.peso = peso;
	}

	public Interesse() {
		this(-1L, -1L, -1L, -1, -1);
	}

	public long getIdInteresse() {
		return idInteresse;
	}

	public void setIdInteresse(long idInteresse) {
		this.idInteresse = idInteresse;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(long idJogo) {
		this.idJogo = idJogo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
}
