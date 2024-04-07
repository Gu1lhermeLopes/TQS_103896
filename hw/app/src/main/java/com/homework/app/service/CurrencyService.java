package com.homework.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

 
@Service
public class CurrencyService {

    private static final String EXCHANGE_RATE_KEY = "81854754e6f65f18141897ed";
    private static final String EXCHANGE_RATE_URL = "https://v6.exchangerate-api.com/v6/"+EXCHANGE_RATE_KEY+"/latest/EUR";

    
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "exchangeRatesCache", key = "#root.methodName")
    public Map<String, Double> getExchangeRates() throws JsonProcessingException {
       
        try {
            String jsonResponse = restTemplate.getForObject(EXCHANGE_RATE_URL, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode conversionRatesNode = rootNode.get("conversion_rates");
    
            Map<String, Double> conversionRates = objectMapper.convertValue(conversionRatesNode, Map.class);
            return conversionRates;
            
        } catch (Exception e) {
            return null;
        }

    }

    


}
