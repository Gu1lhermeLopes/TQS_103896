package com.homework.app.service;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.app.model.Trip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);

    private static final String EXCHANGE_RATES_CACHE_NAME = "exchangeRatesCache";
    private static final String EXCHANGE_RATE_KEY = "81854754e6f65f18141897ed";
    private static final String EXCHANGE_RATE_URL = "https://v6.exchangerate-api.com/v6/" + EXCHANGE_RATE_KEY + "/latest/EUR";
    private static final int DEFAULT_CACHE_TTL_SECONDS = 3600;

    private final RestTemplate restTemplate;
    private final CacheManager cacheManager;

    public CurrencyService(RestTemplate restTemplate, CacheManager cacheManager) {
        this.restTemplate = restTemplate;
        this.cacheManager = cacheManager;

        refreshExchangeRatesCache();
    }



    public List<Trip> convertTripsPrice(List<Trip> trips, String currency){
        Double exchangeRate = getValueByCurrency(currency);
        if (exchangeRate != null) {
            for (Trip trip : trips) {
                trip.setPrice(trip.getPrice() * exchangeRate);
            }
        }
        return trips; 
    }

    public Trip convertTripPrice(Trip trip, String currency){
        Double exchangeRate = getValueByCurrency(currency);
        if (exchangeRate != null) {
            trip.setPrice(trip.getPrice() * exchangeRate);
        }
        return trip; 
    }


    public Double getValueByCurrency(String currency){
        if (currency.equals("EUR")) {
            return 1.0;
        }
        Map<String, Double> exchangeRates = getExchangeRates();
        if (exchangeRates != null) {
            return exchangeRates.get(currency);
        }
        return null;
    }

    public Map<String, Double> getExchangeRates() {
        Cache exchangeRatesCache = cacheManager.getCache(EXCHANGE_RATES_CACHE_NAME);
        if (exchangeRatesCache != null) {
            Cache.ValueWrapper valueWrapper = exchangeRatesCache.get("exchangeRates");
            if (valueWrapper != null) {
                return (Map<String, Double>) valueWrapper.get();
            }
        }
        return Collections.emptyMap();
    }

    public List<String> getSupportedCurrencies() {
        Map<String, Double> exchangeRates = getExchangeRates();
        if (exchangeRates != null) {
            return new ArrayList<>(exchangeRates.keySet());
        }
        return Collections.emptyList();
    }

    private void refreshExchangeRatesCache() {
        String jsonResponse = restTemplate.getForObject(EXCHANGE_RATE_URL, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode conversionRatesNode = rootNode.get("conversion_rates");
            Map<String, Double> exchangeRates = objectMapper.convertValue(conversionRatesNode, Map.class);

            Cache exchangeRatesCache = cacheManager.getCache(EXCHANGE_RATES_CACHE_NAME);
            if (exchangeRatesCache != null) {
                exchangeRatesCache.put("exchangeRates", exchangeRates);
                // Define o tempo de vida do cache
                exchangeRatesCache.put("ttl", System.currentTimeMillis() + (DEFAULT_CACHE_TTL_SECONDS * 1000));
            }
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar cache de taxas de câmbio", e);
        }
    }
}
