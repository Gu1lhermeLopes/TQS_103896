package com.homework.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper; // Import da classe correta
import org.springframework.web.client.RestTemplate;

import com.homework.app.model.Trip;
import com.homework.app.service.CurrencyService;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Cache cache;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    public void setup() {
        when(cacheManager.getCache(anyString())).thenReturn(cache);
    }

    @Test
    void testConvertTripPrice() {
        Trip trip = new Trip();
        trip.setPrice(100.0);

        when(cache.get("exchangeRates")).thenReturn(
            new SimpleValueWrapper(Map.of("USD", 1.2))
        );

        Trip convertedTrip = currencyService.convertTripPrice(trip, "USD");

        assertEquals(100.0 * 1.2, convertedTrip.getPrice());
    }  
}
