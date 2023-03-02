package tqs.stocks;

import java.util.ArrayList;

public class StocksPortfolio{

    private ArrayList<Stock> stocks;
    private IStockmartekService stockmarket;



    public StocksPortfolio(IStockmartekService stockmarket) {
        this.stockmarket = stockmarket;
        stocks =new ArrayList<Stock>();
    }

    public void addStock(Stock s) {
        stocks.add(s);
    }

    public double getTotalValue() {
        
        double value=0;
        for (Stock s : stocks) {
            value += (stockmarket.lookUpPrice(s.getLabel()) * s.getQuantity());
        }
        return value;
        
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public IStockmartekService getStockmarket() {
        return stockmarket;
    }

    



    



}
