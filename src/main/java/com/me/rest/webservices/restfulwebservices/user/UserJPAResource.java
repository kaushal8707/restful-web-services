package com.me.rest.webservices.restfulwebservices.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/user/{id}")
    public Resource<User> getUser(@PathVariable int id)
    {
        Optional<User> user=userRepository.findById(id);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("id-"+id);
        }
        //HATEOAS
        //Retrieve All Users Link...

        Resource<User> resource=new Resource<User>(user.get());
        ControllerLinkBuilder linkTo=ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUser());
        resource.add(linkTo.withRel("all - Users"));
        return resource;
    }

    @PostMapping(path = "/jpa/user/save")
    public ResponseEntity createUser(@Valid @RequestBody User user)
    {
        User savedUser=userRepository.save(user);
        URI location=ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/user/delete/{id}")
    public void deleteUser(@PathVariable int id)
    {
       userRepository.deleteById(id);
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> getAllPosts(@PathVariable int id)
    {
        Optional<User> userOptional= userRepository.findById(id);
        if(!userOptional.isPresent())
        {
            throw new UserNotFoundException("id- "+id);
        }
          return userOptional.get().getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity createUser(@PathVariable int id,@RequestBody Post post)
    {
        Optional<User> userOptional= userRepository.findById(id);
        if(!userOptional.isPresent())
        {
            throw new UserNotFoundException("id- "+id);
        }
        User user=userOptional.get();
        post.setUser(user);
        postRepository.save(post);
        URI location=ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}


