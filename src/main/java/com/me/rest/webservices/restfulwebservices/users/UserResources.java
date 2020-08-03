package com.me.rest.webservices.restfulwebservices.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResources
{
    @Autowired
    UserDAOService userDAOService;

    @GetMapping(path = "/users")
    public List<User> getAllUser()
    {
        return userDAOService.getAll();
    }

    @GetMapping(path = "/user/{id}")
    public Resource<User> getUser(@PathVariable int id)
    {
        User user=userDAOService.getOne(id);
        if(user==null)
        {
            throw new UserNotFoundException("id-"+id);
        }
        //HATEOAS
        //Retrieve All Users Link...

        Resource<User> resource=new Resource<User>(user);
        ControllerLinkBuilder linkTo=ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUser());
        resource.add(linkTo.withRel("all - Users"));
        return resource;
    }

    @PostMapping(path = "/user/save")
    public ResponseEntity createUser(@Valid @RequestBody User user)
    {
        User savedUser=userDAOService.save(user);
        URI location=ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/user/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id)
    {
       User user=userDAOService.deleteUser(id);
       if(user==null)
       {
           throw new UserNotFoundException("id-"+id);
       }
       URI uri=ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(user.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }
}


