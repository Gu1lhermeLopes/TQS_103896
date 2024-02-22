package com.tqs;

import java.util.List;

public class StocksPortfolio {
    private IStockmarketService stockmarket;
    private List<Stock> stocks;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
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