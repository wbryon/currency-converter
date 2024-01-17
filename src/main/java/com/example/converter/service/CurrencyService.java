package com.example.converter.service;

import com.example.converter.entity.CurrencyRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
    @Value("${currency.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private CurrencyRates getCurrencyRates() {
        return restTemplate.getForObject(apiUrl, CurrencyRates.class);
    }

    public CurrencyRates getCurrencyList() {
        return getCurrencyRates();
    }
}
