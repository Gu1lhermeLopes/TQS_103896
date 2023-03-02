package tqs.stocks;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import tqs.stocks.*;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
public class StockTest {

    @Mock
    IStockmartekService market;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    public void testMockPortfolio() {

        Stock a= new Stock("alibaba",4);
        Stock b= new Stock("amazon",3);
        
        portfolio.addStock(a);
        portfolio.addStock(b);

        Mockito.when(market.lookUpPrice("alibaba")).thenReturn(4.5);
        Mockito.when(market.lookUpPrice("amazon")).thenReturn(10.0);

        double answer = 4*4.5 + 3*10.0;

        //assertEquals(answer, portfolio.getTotalValue());
        assertThat(answer,is(portfolio.getTotalValue()));

        Mockito.verify(market, Mockito.times(2)).lookUpPrice(Mockito.anyString());

        
    }

}
