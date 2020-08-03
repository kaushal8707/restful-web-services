package com.me.rest.webservices.restfulwebservices.welcome;

import org.springframework.stereotype.Component;

@Component
public class WelcomeService
{
    public String showMessage()
    {
        return "Welcome to my Portal";
    }
}
