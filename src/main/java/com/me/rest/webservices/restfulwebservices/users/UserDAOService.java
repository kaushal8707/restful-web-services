package com.me.rest.webservices.restfulwebservices.users;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService
{
    private static List<User> list=new ArrayList<>();
    private static int countUser=3;
    static{
        list.add(new User(1,"kaushal",new Date()));
        list.add(new User(2,"Kanishk",new Date()));
        list.add(new User(3,"Raju",new Date()));
    }
    public List<User> getAll()
    {
        return list;
    }
    public User save(User user)
    {
        if(user.getId()==null)
        {
            user.setId(++countUser);
        }
        list.add(user);
        return user;
    }
    public User deleteUser(int id)
    {
        Iterator<User> iterator=list.iterator();
        while(iterator.hasNext())
        {
            User user=iterator.next();
            int id1=user.getId();
            if(id1==id)
            {
                iterator.remove();
            }
            return user;
        }
        return null;
    }
    public User getOne(int id)
    {
        for(User user:list)
        {
            if (user.getId()==id)
            {
                return user;
            }
        }
        return null;
    }
}
