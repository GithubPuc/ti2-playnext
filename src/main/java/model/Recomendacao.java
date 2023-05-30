package model;

public class Recomendacao {
	private long idRecomendacao;
	private long idUsuario;
	private long idJogo;
	private int confianca;
	private int tipo;

	public Recomendacao(long idRecomendacao, long idUsuario, long idJogo, int confianca, int tipo) {
		this.idRecomendacao = idRecomendacao;
		this.idUsuario = idUsuario;
		this.idJogo = idJogo;
		this.confianca = confianca;
		this.tipo = tipo;
	}

	public Recomendacao() {
		this(-1L, -1L, -1L, -1, -1);
	}

	public long getIdRecomendacao() {
		return idRecomendacao;
	}

	public void setIdRecomendacao(long idRecomendacao) {
		this.idRecomendacao = idRecomendacao;
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
