package com.example.converter.controller;

import com.example.converter.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/list/{base}")
    public Map<String, Double> getCurrencyList(@PathVariable String base) {
        return currencyService.getCurrencyList(base);
    }

    @GetMapping("/convert")
    public Double convertCurrency(@RequestParam String from,
                                  @RequestParam String to,
                                  @RequestParam Double amount) {
        return currencyService.convertCurrency(from, to, amount);
    }
}
