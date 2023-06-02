package model;

public class TagLink {
	private long idJogo;
	private long idTag;

	public TagLink(long idJogo, long idTag) {
		this.idJogo = idJogo;
		this.idTag = idTag;
	}

	public TagLink() {
		this(-1L, -1L);
	}

	public long getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(long idJogo) {
		this.idJogo = idJogo;
	}

	public long getIdTag() {
		return idTag;
	}

	public void setIdTag(long idTag) {
		this.idTag = idTag;
	}
}
