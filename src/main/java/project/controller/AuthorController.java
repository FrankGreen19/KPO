package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.model.Author;
import project.resource.AuthorResource;
import project.resource.UserResource;
import project.service.AuthorRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("")
    List<AuthorResource> getAll() throws SQLException {
        ArrayList<AuthorResource> authors = new ArrayList<>();

        for (Author author : authorRepository.select()) {
            authors.add(author.toResource());
        }

        return authors;
    }

    @GetMapping("/{id}")
    AuthorResource get(@PathVariable int id) throws SQLException {
        return authorRepository.select(id).toResource();
    }

    @PostMapping("")
    AuthorResource post(@RequestBody AuthorResource authorResource) throws SQLException {
        return authorRepository.insert(authorResource.toEntity()).toResource();
    }

    @PutMapping(value = "/{id}")
    AuthorResource put(@PathVariable Integer id, @RequestBody AuthorResource authorResource) throws SQLException {
        return authorRepository.update(id, authorResource.toEntity()).toResource();
    }

    @DeleteMapping("/{id}")
    AuthorResource delete(@PathVariable Integer id) throws SQLException {
        return authorRepository.delete(id).toResource();
    }
}
