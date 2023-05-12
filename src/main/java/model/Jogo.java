package model;

public class Jogo {
	private long idJogo;
	private String titulo;
	private String descricao;
	private String url;
	private String display;
	private int pontuacao;

	public Jogo(long idJogo, String titulo, String descricao, String url, String display, int pontuacao) {
		this.idJogo = idJogo;
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
		this.display = display;
		this.pontuacao = pontuacao;
	}

	public Jogo() {
		this(-1, null, null, null, null, -1);
	}

	public long getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(long idJogo) {
		this.idJogo = idJogo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
}
