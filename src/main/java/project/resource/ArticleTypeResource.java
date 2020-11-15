package project.resource;

import project.model.ArticleType;

import java.io.Serializable;

public class ArticleTypeResource extends BaseResource {

    private Integer id;
    private String name;

    public ArticleTypeResource() {
    }

    public ArticleTypeResource(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ArticleTypeResource(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArticleType toEntity() {
        return new ArticleType(
                this.id,
                this.name
        );
    }
}
