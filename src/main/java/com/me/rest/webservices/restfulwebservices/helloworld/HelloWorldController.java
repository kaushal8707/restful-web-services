package com.me.rest.webservices.restfulwebservices.helloworld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // @RequestMapping(method = RequestMethod.GET,path = "/helloWorld")
    @GetMapping(path = "/helloWorld")
    public String getHelloWorld() {
        return "Hello-World !!";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean getHelloWorldBean()
    {
        return new HelloWorldBean("Hello World Bean !!");
    }
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name)
    {
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }
}
