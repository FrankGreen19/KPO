package project.model;

import project.resource.AuthorResource;
import project.resource.BaseResource;

public class Author extends BaseEntity {

    private String name;
    private String surname;
    private Integer article_num;

    public Author(Integer id) {
        super(id);
    }

    public Author(Integer id, String name, String surname, int article_num) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.article_num = article_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getArticle_num() {
        return article_num;
    }

    public void setArticle_num(Integer article_num) {
        this.article_num = article_num;
    }

    public AuthorResource toResource() {
        return new AuthorResource(
                this.getId(),
                this.name,
                this.surname,
                this.article_num
        );
    }
}
