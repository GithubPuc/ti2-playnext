package model;

public class Tag {
    private Long idTag;
    private String tagName;
    private String tagDesc;

    public Tag(Long idTag, String tagName, String tagDesc) {
        this.idTag = idTag;
        this.tagName = tagName;
        this.tagDesc = tagDesc;
    }

    public Tag() {
        this(-1L, null, null);
    }

    public Long getIdTag() {
        return idTag;
    }

    public void setIdTag(Long idTag) {
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
