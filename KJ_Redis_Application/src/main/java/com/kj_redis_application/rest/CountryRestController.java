package com.kj_redis_application.rest;

import com.kj_redis_application.binding.Country;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
public class CountryRestController {

    private HashOperations<String, Integer, Country> opsForHash = null;

    public CountryRestController(RedisTemplate<String, Country> rt) {
        this.opsForHash = rt.opsForHash();
    }

    @PostMapping("/country")
    public String addCountry(@RequestBody Country country) {
        System.out.println("Saving country with sno: " + country.getSno());
        opsForHash.put("COUNTRIES", country.getSno(), country);
        return "Country added";
    }

    @GetMapping("/countries")
    public Collection<Country> getCountries() {
        Map<Integer,Country> entries = opsForHash.entries("COUNTRIES");
        Collection<Country> values = entries.values();
        return values;
    }


}
