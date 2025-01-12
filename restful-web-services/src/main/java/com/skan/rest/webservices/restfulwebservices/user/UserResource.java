package com.skan.rest.webservices.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserResource {
    private UserDaoService service;
    public UserResource(UserDaoService userDaoService){
        this.service = userDaoService;
    }

    @GetMapping(path = "/users", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    /*
     for HATEOAS , wrap the User in Entity Model and WebMvcLinkBuilder

     return user and link for users
    */

    @GetMapping(value = "/users/{id}" )
    public EntityModel<User> retrieveUserById(@PathVariable int id){
        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id:"+id);
        }

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
       User savedUser=  service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                    .path("/{id}")
                                                    .buildAndExpand(savedUser.getId())
                                                    .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);

    }
}
