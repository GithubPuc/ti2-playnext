package model;

public class Aventura {
	private long idAventura;
	private long idUsuario;
	private long idJogo;
	private int confianca;
	private int tipo;

	public Aventura(long idAventura, long idUsuario, long idJogo, int confianca, int tipo) {
		this.idAventura = idAventura;
		this.idUsuario = idUsuario;
		this.idJogo = idJogo;
		this.confianca = confianca;
		this.tipo = tipo;
	}

	public Aventura() {
		this(-1L, -1L, -1L, -1, -1);
	}

	public long getIdAventura() {
		return idAventura;
	}

	public void setIdAventura(long idAventura) {
		this.idAventura = idAventura;
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

	public int getConfianca() {
		return confianca;
	}

	public void setConfianca(int confianca) {
		this.confianca = confianca;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
