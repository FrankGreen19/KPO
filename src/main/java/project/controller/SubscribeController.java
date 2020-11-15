package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.model.Article;
import project.model.Subscribe;
import project.resource.ArticleResource;
import project.resource.SubscribeResource;
import project.service.ArticleRepository;
import project.service.SubscribeRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    @Autowired
    SubscribeRepository subscribeRepository;

    @GetMapping("")
    List<SubscribeResource> getAll() throws SQLException {
        ArrayList<SubscribeResource> subscribes = new ArrayList<>();

        for (Subscribe subscribe : subscribeRepository.select()) {
            subscribes.add(subscribe.toResource());
        }

        return subscribes;
    }

    @GetMapping("/{id}")
    SubscribeResource get(@PathVariable int id) throws SQLException {
        return subscribeRepository.select(id).toResource();
    }

    @PostMapping("")
    SubscribeResource post(@RequestBody SubscribeResource subscribeResource) throws SQLException {
        return subscribeRepository.insert(subscribeResource.toEntity()).toResource();
    }

    @PutMapping(value = "/{id}")
    SubscribeResource put(@PathVariable Integer id, @RequestBody SubscribeResource subscribeResource) throws SQLException {
        return subscribeRepository.update(id, subscribeResource.toEntity()).toResource();
    }

    @DeleteMapping("/{id}")
    SubscribeResource delete(@PathVariable Integer id) throws SQLException {
        return subscribeRepository.delete(id).toResource();
    }
}
