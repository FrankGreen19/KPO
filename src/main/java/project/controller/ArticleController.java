package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.model.Article;
import project.resource.ArticleResource;
import project.service.ArticleRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("")
    List<ArticleResource> getAll() throws SQLException {
        ArrayList<ArticleResource> articleResources = new ArrayList<>();

        for (Article article : articleRepository.select()) {
            articleResources.add(article.toResource());
        }

        return articleResources;
    }

    @GetMapping("/{id}")
    ArticleResource get(@PathVariable int id) throws SQLException {
        return articleRepository.select(id).toResource();
    }

    @PostMapping("")
    ArticleResource post(@RequestBody ArticleResource articleResource) throws SQLException {
        return articleRepository.insert(articleResource.toEntity()).toResource();
    }

    @PutMapping(value = "/{id}")
    ArticleResource put(@PathVariable Integer id, @RequestBody ArticleResource articleResource) throws SQLException {
        return articleRepository.update(id, articleResource.toEntity()).toResource();
    }

    @DeleteMapping("/{id}")
    ArticleResource delete(@PathVariable Integer id) throws SQLException {
        return articleRepository.delete(id).toResource();
    }
}
