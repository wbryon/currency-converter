package com.example.converter.service;

import com.example.converter.entity.CurrencyRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.lang.Math.round;

@Service
public class CurrencyService {

    @Value("${api.url}")
    private String apiUrl;


    private final RestTemplate restTemplate;

    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Double> getCurrencyList(java.lang.String baseCurrency) {
        CurrencyRates currencyRates = getCurrencyRates(baseCurrency);
        return currencyRates.getRates();
    }

    public Double convertCurrency(String from, String to, Double amount) {
        CurrencyRates currencyRates = getCurrencyRates(from.toUpperCase());

        if (currencyRates != null) {
            Map<String, Double> rates = currencyRates.getRates();
            Double fromRate = rates.get(from.toUpperCase());
            Double toRate = rates.get(to.toUpperCase());

            if (fromRate != null && toRate != null) {
                double result = amount * (toRate / fromRate);

                return round(result * 100.0) / 100.0;
            }
            throw new IllegalArgumentException("Invalid currency code");
        }
        throw new IllegalArgumentException("Unable to retrieve currency rates");
    }


    private CurrencyRates getCurrencyRates(String baseCurrency) {
        String currencyApiUrl = String
                .format("%s/latest?api_key=3UsccLxLgZZIvWHXaZPGMvjWUKljt3P0&base=%s", apiUrl, baseCurrency.toUpperCase());
        return restTemplate.getForObject(currencyApiUrl, CurrencyRates.class);
    }
}
