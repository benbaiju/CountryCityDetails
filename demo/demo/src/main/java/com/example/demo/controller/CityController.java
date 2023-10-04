package com.example.demo.controller;

import com.example.demo.entity.Countries;
import com.example.demo.entity.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CityController {
    List<Countries> countries=new ArrayList<>();


    @GetMapping("/countries")
    public List<Countries> getCountryDetails(){
        String uri = "https://countriesnow.space/api/v0.1/countries";
        RestTemplate template = new RestTemplate();
        Object[] objects = new Object[]{template.getForObject(uri, Object.class)};
        ObjectMapper objectMapper = new ObjectMapper();
        countries = Arrays.stream(objects)
                .map(o -> objectMapper.convertValue(o, DataResponse.class))
                .map(DataResponse::getData)
                .findFirst()
                .orElse(new ArrayList<>()); // Default to an empty list if no data is found
        return countries;


    }
    @GetMapping("/city")
    public List<Countries> getCity(){
        return countries.stream().filter(e->e.getCountry().equals("India")).collect(Collectors.toList());
    }

    @GetMapping("/city/{countryName}")
    public List<Countries> getCity(@PathVariable String countryName) {
        return countries.stream()
                .filter(e -> e.getCountry().equalsIgnoreCase(countryName))
                .collect(Collectors.toList());
    }
//    @PostMapping("/city/population")
//    public String getCityPopulation(@RequestBody String cityName) {
//        String apiUrl = "https://countriesnow.space/api/v0.1/countries/population/cities";
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> httpEntity = new HttpEntity<>(cityName, headers);
//
//        return restTemplate.postForObject(apiUrl, httpEntity, String.class);
//    }



}
