package model;

public class Tag {
	private long idTag;
	private String tagName;
	private String tagDesc;

	public Tag(long idTag, String tagName, String tagDesc) {
		this.idTag = idTag;
		this.tagName = tagName;
		this.tagDesc = tagDesc;
	}

	public Tag() {
		this(-1L, null, null);
	}

	public long getIdTag() {
		return idTag;
	}

	public void setIdTag(long idTag) {
		this.idTag = idTag;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagDesc() {
		return tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
}
