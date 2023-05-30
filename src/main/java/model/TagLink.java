package model;

public class TagLink {
	private Long idJogo;
	private Long idTag;

	public TagLink(Long idJogo, Long idTag) {
		this.idJogo = idJogo;
		this.idTag = idTag;
	}

	public TagLink() {
		this(-1L, -1L);
	}

	public Long getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Long idJogo) {
		this.idJogo = idJogo;
	}

	public Long getIdTag() {
		return idTag;
	}

	public void setIdTag(Long idTag) {
		this.idTag = idTag;
	}
}
