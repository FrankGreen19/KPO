package project.resource;

import project.model.Author;

public class AuthorResource extends BaseResource {

    private Integer id;
    private String name;
    private String surname;
    private Integer article_num;

    public AuthorResource() {}

    public AuthorResource(Integer id, String name, String surname, int article_num) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.article_num = article_num;
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

    public Author toEntity() {
        return new Author(
                this.id,
                this.name,
                this.surname,
                this.article_num
        );
    }
}
