package testpackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import simplestock.exceptions.SimpleStockTradingException;
import simplestock.implemented_model.StockImpl;
import simplestock.implemented_model.TradingOrderImpl;
import simplestock.implemented_util.StockCalculatorUtilImpl;
import simplestock.interface_model.BuyOrSellEnum;
import simplestock.interface_model.Stock;
import simplestock.interface_model.StockTypeEnum;
import simplestock.interface_model.TradingOrder;
import simplestock.interface_util.StockCalculatorUtil;

public class SockTraderTest {

	
	
	
	StockCalculatorUtil stockCalculatorUtil = (StockCalculatorUtil) new StockCalculatorUtilImpl();

    @Test
    public void testCalculateSingleStockPrice() throws SimpleStockTradingException {
      
        Stock tea = new StockImpl("TEA", StockTypeEnum.COMMON, 0d, null, 100d, 110d);

        TradingOrder trade1 = new TradingOrderImpl(tea, new Date(), 1000d, BuyOrSellEnum.BUY, 110d);
        TradingOrder trade2 = new TradingOrderImpl(tea, new Date(), 300d, BuyOrSellEnum.SELL, 102d);
      
        List<TradingOrder> trades = new ArrayList<TradingOrder>();
        trades.add(trade1);
        trades.add(trade2);
      
		  Assert.assertEquals(102.0,
				  stockCalculatorUtil.calculateVolumeWeightedStockPrice(trades), 0d);
    }
  
   
    @Test
    public void testCalculateGbceShares() {
        
        Stock tea = new StockImpl("TEA", StockTypeEnum.COMMON, 0d, null, 100d, 110d);
        Stock pop = new StockImpl("POP", StockTypeEnum.COMMON, 8d, null, 100d, 120d);
        Stock ale = new StockImpl("ALE", StockTypeEnum.COMMON, 23d, null, 60d, 55d);
        Stock gin = new StockImpl("GIN", StockTypeEnum.PREFERRED, 8d, 2d, 100d, 100d);
        Stock joe = new StockImpl("JOE", StockTypeEnum.COMMON, 13d, null, 250d, 216.12d);
      
        List<Stock> stocks = new ArrayList<Stock>();
        stocks.add(tea);
        stocks.add(pop);
        stocks.add(ale);
        stocks.add(gin);
        stocks.add(joe);

		  Assert.assertEquals(0.17938927757244394d,
				  stockCalculatorUtil.calculateGbceShares(stocks), 0d);
    }

    
    
	 @Test
    public void testCalculateDividendYieldCommon()
    {
        Assert.assertEquals(0.06d,
        		stockCalculatorUtil.calculateDividendYieldCommon(25d, 1.5d), 0d);
      
		  Assert.assertEquals(0d,
				  stockCalculatorUtil.calculateDividendYieldCommon(25d, 0d), 0d);

        double result = stockCalculatorUtil.calculateDividendYieldCommon(0d, 1.5d);
      
        Assert.assertTrue(result == Double.POSITIVE_INFINITY ||
                    result == Double.NEGATIVE_INFINITY);
    }

  	 @Test
    public void testCalculateDividendYieldPreferred()
    {
        Assert.assertEquals(0.4d,
        		stockCalculatorUtil.calculateDividendYieldPreferred(25d, 20d, 0.5d), 0d);
      
		  Assert.assertEquals(0d,
				  stockCalculatorUtil.calculateDividendYieldPreferred(25d, 0d, 0.5d), 0d);

		  Assert.assertEquals(0d,
				  stockCalculatorUtil.calculateDividendYieldPreferred(25d, 20d, 0d), 0d);

        double result = stockCalculatorUtil.calculateDividendYieldPreferred(0d, 20d, 0.5d);
      
        Assert.assertTrue(result == Double.POSITIVE_INFINITY ||
                    result == Double.NEGATIVE_INFINITY);
    }


  	
	 @Test
    public void testCalculatePeRatio()
    {
        Assert.assertEquals(22.05128205128205d,
        		stockCalculatorUtil.calculatePeRatio(43d, 1.95d), 0d);
      
		  Assert.assertEquals(0d,
				  stockCalculatorUtil.calculatePeRatio(0d, 1.95d), 0d);

        double result = stockCalculatorUtil.calculatePeRatio(43d, 0d);
      
        Assert.assertTrue(result == Double.POSITIVE_INFINITY ||
                    result == Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testCalculateGeometricMean()
    {
       

        Assert.assertEquals(0d,
        		stockCalculatorUtil.calculateGeometricMean(0d), 0d);

        Assert.assertEquals(23.321576831999096,
        		stockCalculatorUtil.calculateGeometricMean(43d, 1.95d, 56d, 63d), 0d);
      
    }	

   
	 @Test
    public void testCalculateStockPrice() throws SimpleStockTradingException
    {
      
        Assert.assertEquals(0d,
        		stockCalculatorUtil.calculateStockPrice(null, null), 0d);

        try {
          
        	stockCalculatorUtil.calculateStockPrice(new double[] {24d, 13d, 2.5d}, null);
          
            Assert.fail("A StockCalculatorException must have been thrown");
        
        } catch (SimpleStockTradingException sce) {}

        try {
          
        	stockCalculatorUtil.calculateStockPrice(
              	 new double[] {24d, 13d, 2.5d}, new double[] {4d, 8d, 10d, 12d});
          
            Assert.fail("A StockCalculatorException must have been thrown");
        
        } catch (SimpleStockTradingException sce) {}

        
       double res= stockCalculatorUtil.calculateStockPrice(
             	 new double[] {21d, 13d, 2.5d}, new double[] {4d, 13d, 10d});
      
        Assert.assertEquals(8.434782608695652d,res, 0d);
    }
    
    
    
}
