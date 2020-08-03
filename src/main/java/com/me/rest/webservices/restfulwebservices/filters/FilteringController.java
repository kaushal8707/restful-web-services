package com.me.rest.webservices.restfulwebservices.filters;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
@RestController
public class FilteringController
{
   @GetMapping("/get-some-bean")
   public MappingJacksonValue getSomeBean()
   {
       SomeBean someBean=new SomeBean("f1","f2","f3");
       SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
       FilterProvider filterProvider=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
       MappingJacksonValue mapping=new MappingJacksonValue(someBean);
       mapping.setFilters(filterProvider);
       return mapping;
   }
    @GetMapping("/get-some-bean-list")
    public MappingJacksonValue getListOfSomeBean()
    {
        List<SomeBean> list=Arrays.asList(new SomeBean("f1","f2","f3"),
                             new SomeBean("f11","f12","f13"));
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
        FilterProvider filterProvider=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mapping=new MappingJacksonValue(list);
        mapping.setFilters(filterProvider);
        return mapping;
    }
}