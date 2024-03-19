package com.example.converter.service;

import com.example.converter.entity.CurrencyRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.round;

@Service
public class CurrencyService {

    @Value("${api.url}")
    private String apiUrl;

    private final WebClient webClient;

    public CurrencyService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Map<String, Double>> getCurrencyListMono(String baseCurrency) {
        return getCurrencyRatesMono(baseCurrency)
                .map(currencyRates -> new TreeMap<>(currencyRates.getRates()));
    }

    public Mono<CurrencyRates> getCurrencyRatesMono(String baseCurrency) {
        String currencyApiUrl = String
                .format("%s/latest?api_key=3UsccLxLgZZIvWHXaZPGMvjWUKljt3P0&base=%s", apiUrl, baseCurrency.toUpperCase());

        return this.webClient.get()
                .uri(currencyApiUrl)
                .retrieve()
                .bodyToMono(CurrencyRates.class);
    }

    public Mono<Double> convertCurrencyMono(String from, String to, Double amount) {
        return getCurrencyRatesMono(from.toUpperCase())
                .flatMap(currencyRates -> {
                    Map<String, Double> rates = currencyRates.getRates();
                    Double fromRate = rates.get(from.toUpperCase());
                    Double toRate = rates.get(to.toUpperCase());

                    if (fromRate != null && toRate != null) {
                        double result = amount * (toRate / fromRate);
                        return Mono.just(round(result * 100.0) / 100.0);
                    } else {
                        return Mono.error(new IllegalArgumentException("Invalid currency code"));
                    }
                })
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Unable to retrieve currency rates")));
    }
}
