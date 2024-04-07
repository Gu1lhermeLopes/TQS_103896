package com.homework.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import com.homework.app.service.CurrencyService;

@Controller
@RequestMapping("/money")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/main")
    public String printExchangeRates(Model model) {
        Map<String, Double> exchangeRates = currencyService.getExchangeRates();
        
        model.addAttribute("exchangeRates", exchangeRates);
        
        return "exchange-rates-view";
    }
}
