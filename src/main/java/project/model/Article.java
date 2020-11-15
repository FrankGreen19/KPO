package project.model;

import project.resource.ArticleResource;

import java.util.Date;

public class Article extends BaseEntity {

    private Integer articleTypeId;
    private Integer authorId;
    private String header;
    private String main;
    private Date date;
    private boolean priority;

    public Article(Integer id) {
        super(id);
    }

    public Article(Integer id, Integer articleTypeId, Integer authorId, String header, String main, Date date, boolean priority) {
        super(id);
        this.articleTypeId = articleTypeId;
        this.authorId = authorId;
        this.header = header;
        this.main = main;
        this.date = date;
        this.priority = priority;
    }

    public Integer getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(Integer articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public ArticleResource toResource() {
        return new ArticleResource(
            this.getId(),
            this.articleTypeId,
            this.authorId,
            this.header,
            this.main,
            this.date,
            this.priority
        );
    }
}
