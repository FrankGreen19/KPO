package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.model.User;
import project.resource.UserResource;
import project.service.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "")
    List<UserResource> getAll() throws SQLException {
        ArrayList<UserResource> userResources = new ArrayList<>();

        for (User user : userRepository.select()) {
            userResources.add(user.toResource());
        }

        return userResources;
    }

    @GetMapping(value = "/{id}")
    UserResource get(@PathVariable Integer id) throws SQLException {
        return userRepository.select(id).toResource();
    }

    @PostMapping(value = "")
    UserResource post(@RequestBody UserResource userResource) throws SQLException {

        return userRepository.insert(userResource.toEntity()).toResource();
    }

    @PutMapping(value = "/{id}")
    UserResource put(@PathVariable Integer id, @RequestBody UserResource userResource) throws SQLException {
        return userRepository.update(id, userResource.toEntity()).toResource();
    }

    @DeleteMapping(value = "/{id}")
    UserResource delete(@PathVariable Integer id) throws SQLException {
        return userRepository.delete(id).toResource();
    }
}
