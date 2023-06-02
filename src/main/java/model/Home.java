package model;

public class Home {
	private long idHome;
	private String titulo;
	private String descricao;
	private String url;
	private String display;
	private int pontuacao;

	public Home(long idHome, String titulo, String descricao, String url, String display, int pontuacao) {
		this.idHome = idHome;
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
		this.display = display;
		this.pontuacao = pontuacao;
	}

	public Home() {
		this(-1, null, null, null, null, -1);
	}

	public long getIdHome() {
		return idHome;
	}

	public void setIdHome(long idHome) {
		this.idHome = idHome;
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
