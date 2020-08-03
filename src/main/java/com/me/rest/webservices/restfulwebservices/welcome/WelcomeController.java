package com.me.rest.webservices.restfulwebservices.welcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Tightly Couple
@RestController
public class WelcomeController
{
    WelcomeService service=new WelcomeService();

    @GetMapping(path="/welcome")
    public String welcome()
    {
       return service.showMessage();
    }
}


//Loosely Coupled Example...
@RestController
 class WelcomeMyController
{
    @Autowired
    WelcomeService welcomeService;
    @RequestMapping(method = RequestMethod.GET,path = "welcometo")
    public String getWelcomeMsg()
    {
        return welcomeService.showMessage();
    }
}
