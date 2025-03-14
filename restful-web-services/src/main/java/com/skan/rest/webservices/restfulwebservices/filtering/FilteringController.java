package com.skan.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering") //field2
    public SomeBean filtering() {
        //for static filtering use  //JsonIgnoreProperties or JsonIgnore
        return new SomeBean("value1","value2", "value3");
    }

    @GetMapping("/filtering-list") //field2
    public List<SomeBean> filteringList() {
        //for static filtering use  //JsonIgnoreProperties or JsonIgnore
        return Arrays.asList(new SomeBean("value1","value2", "value3"),
                new SomeBean("value1","value2", "value3")) ;
    }

    @GetMapping("/filtering-dynamic") //field2
    public MappingJacksonValue filteringDynamic() {

        SomeBean someBean = new SomeBean("value1","value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
        //@JsonFilter("SomeBeanFilter") add this to User object

        mappingJacksonValue.setFilters(filters);


        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list-dynamic") //field2, field3
    public MappingJacksonValue filteringListDynamic() {
        List<SomeBean> list = Arrays.asList(new SomeBean("value1","value2", "value3"),
                new SomeBean("value4","value5", "value6"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");

        FilterProvider filters =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );

        mappingJacksonValue.setFilters(filters );


        return mappingJacksonValue;
    }

}
