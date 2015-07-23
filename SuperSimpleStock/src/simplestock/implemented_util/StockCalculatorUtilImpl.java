package simplestock.implemented_util;

import java.util.List;

import simplestock.exceptions.SimpleStockTradingException;
import simplestock.interface_model.Stock;
import simplestock.interface_model.TradingOrder;
import simplestock.interface_util.StockCalculatorUtil;


public class StockCalculatorUtilImpl implements StockCalculatorUtil {
  	
  	
    @Override
    public double calculateDividendYieldCommon(double tickerPrice, double lastDividend) {
      
      	return lastDividend / tickerPrice;
    }

  	
    @Override
    public double calculateDividendYieldPreferred(double tickerPrice, double parValue, double fixedDividend) {
      
      	return (fixedDividend * parValue) / tickerPrice;
    }
  
  	
    @Override
    public double calculatePeRatio(double tickerPrice, double lastDividend) {
      	
      	return tickerPrice / lastDividend;
    }

   
    @Override
    public double calculateGeometricMean(double... tradesPrices) {
      
        if (tradesPrices == null || (tradesPrices != null && tradesPrices.length == 0)) {
            
            return 0d;
        }
      
        double geometricMean = tradesPrices[0];
      
        for(int i = 1; i < tradesPrices.length; i++) {
          
            geometricMean *= tradesPrices[i];
        }
      
        Integer n = new Integer(tradesPrices.length);
      
        return Math.pow(geometricMean, 1.0d / n.doubleValue());
    }
  
    
    public double calculateStockPrice(double[] tradesPrices, double[] tradesQuantities) throws SimpleStockTradingException {
      
		  if (tradesPrices == null || (tradesPrices != null && tradesPrices.length == 0)) {
            
            return 0d;
        }
      
        if (tradesQuantities == null) {
            
            throw new SimpleStockTradingException("trades quantities array is null !");
        }

        if (tradesPrices.length != tradesQuantities.length) {
          
            throw new SimpleStockTradingException("trades prices and quantities arrays are not the same length !");
        }
      
		  double pricesPerQuantities = 0d;
        double quantities = 0d;
      
        for(int i = 1; i < tradesPrices.length; i++) {
          
            pricesPerQuantities += tradesPrices[i] * tradesQuantities[i];
          
            quantities += tradesQuantities[i];
        }
      
        return pricesPerQuantities / quantities;
    }
    
    
    
    @Override
    public double calculateVolumeWeightedStockPrice(List<TradingOrder> trades) throws SimpleStockTradingException {
      
        if (trades == null) {
          
            return 0d;
        }
      
        double[] tradesPrices = new double[trades.size()];
        double[] tradesQuantities = new double[trades.size()];
      
      
        int  i = 0;
        for(TradingOrder trade : trades) {
          
            tradesPrices[i] = trade.getPrice();
            tradesQuantities[i] = trade.getSharesQuantity();
          
            i++;
        }
          
        return calculateStockPrice(tradesPrices, tradesQuantities);
    }

    @Override
    public double calculateGbceShares(List<Stock> stocks) {

        double[] tradesPrices = new double[stocks.size()];
      
        double totalParValues = 0d;

        int  i = 0;
        for(Stock stock : stocks) {
          
            totalParValues += stock.getParValue();
          
            tradesPrices[i] = stock.getMarketPrice();
          
            i++;
        }

        double geometricMean = calculateGeometricMean(tradesPrices);
        
        return geometricMean / totalParValues;        
    }

   
}
