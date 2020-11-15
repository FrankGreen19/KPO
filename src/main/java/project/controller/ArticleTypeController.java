package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.model.ArticleType;
import project.model.Author;
import project.resource.ArticleTypeResource;
import project.resource.AuthorResource;
import project.service.ArticleTypeRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/articletype")
public class ArticleTypeController {

    @Autowired
    ArticleTypeRepository articleTypeRepository;

    @GetMapping("")
    List<ArticleTypeResource> getAll() throws SQLException {
        ArrayList<ArticleTypeResource> articleTypeResources = new ArrayList<>();

        for (ArticleType articleType : articleTypeRepository.select()) {
            articleTypeResources.add(articleType.toResource());
        }

        return articleTypeResources;
    }

    @GetMapping("/{id}")
    ArticleTypeResource get(@PathVariable int id) throws SQLException {
        return articleTypeRepository.select(id).toResource();
    }

    @PostMapping("")
    ArticleTypeResource insert(@RequestBody ArticleType articleType) throws SQLException {
        return articleTypeRepository.insert(articleType).toResource();
    }

    @PutMapping("/{id}")
    ArticleTypeResource update(@PathVariable Integer id, @RequestBody ArticleType articleType) throws SQLException {
        return articleTypeRepository.update(id, articleType).toResource();
    }

    @DeleteMapping("/{id}")
    ArticleTypeResource delete(@PathVariable Integer id) throws SQLException {
        return articleTypeRepository.delete(id).toResource();
    }
}
