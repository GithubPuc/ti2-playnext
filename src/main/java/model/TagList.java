package model;

public class TagList {
    private Long idJogo;
    private Long idTag;

    public TagList(Long idJogo, Long idTag) {
        this.idJogo = idJogo;
        this.idTag = idTag;
    }

    public TagList() {
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
