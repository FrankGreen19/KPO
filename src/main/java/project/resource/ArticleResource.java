package project.resource;

import project.model.Article;

import java.util.Date;

public class ArticleResource extends BaseResource {

    private int id;
    private Integer articleTypeId;
    private Integer authorId;
    private String header;
    private String main;
    private Date date;
    private boolean priority;

    public ArticleResource() {
    }

    public ArticleResource(int id, Integer articleTypeId, Integer authorId, String header, String main, Date date, boolean priority) {
        this.id = id;
        this.articleTypeId = articleTypeId;
        this.authorId = authorId;
        this.header = header;
        this.main = main;
        this.date = date;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Article toEntity() {
        return new Article(
                this.id,
                this.articleTypeId,
                this.authorId,
                this.header,
                this.main,
                this.date,
                this.priority
        );
    }
}
