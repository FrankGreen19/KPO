package project.model;

import project.resource.ArticleTypeResource;

public class ArticleType extends BaseEntity{

    private String name;

    public ArticleType(Integer id) {
        super(id);
    }

    public ArticleType(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArticleTypeResource toResource() {
        return new ArticleTypeResource(
                this.getId(),
                this.name
        );
    }
}
