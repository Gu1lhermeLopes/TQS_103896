package com.tqs;

import java.util.ArrayList;

public class StocksPortfolio {
    private IStockmarketService stockmarket;
    private ArrayList<Stock> stocks;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
        stocks = new ArrayList<Stock>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double totalValue(){
        double total=0;
        for (Stock s : stocks) {
            total += s.getQuantity() * stockmarket.lookUpPrice(s.getLabel());
        }
        return total;
    } 
}