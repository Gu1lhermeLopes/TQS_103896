package com.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(MockitoExtension.class)
public class StocksTest {

    @Mock
    IStockmarketService service;

    @InjectMocks
    StocksPortfolio portfolio;

    Stock aStock = new Stock("apple", 2);
    Stock bStock = new Stock("banana", 3);
    Stock cStock = new Stock("chips", 4);



    @Test
    public void testTotalValue(){

        double price = 2.0;
        when(service.lookUpPrice(any(String.class))).thenReturn(price);

        portfolio.addStock(aStock);
        portfolio.addStock(bStock);
        portfolio.addStock(cStock);

        double expectedTotal = (aStock.getQuantity() + bStock.getQuantity() + cStock.getQuantity()) * price;
        //assertEquals(portfolio.totalValue(), expectedTotal);
        //verify(service, times(3)).lookUpPrice(anyString());

        assertThat(portfolio.totalValue(), is(expectedTotal));
        verify(service, times(3)).lookUpPrice(anyString());
    } 
}