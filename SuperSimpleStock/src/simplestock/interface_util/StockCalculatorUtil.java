package simplestock.interface_util;

import java.util.List;

import simplestock.exceptions.SimpleStockTradingException;
import simplestock.interface_model.Stock;
import simplestock.interface_model.TradingOrder;


public interface StockCalculatorUtil {
  
  	/**
  	 * For a given stock of common type ticker price and dividend, calculates the dividend yield.
  	 * @param  tickerPrice  the stock ticker price
  	 * @param  lastDividend the last dividend of the stock
  	 * @return the stock dividend yield
  	 */
    double calculateDividendYieldCommon(double tickerPrice, double lastDividend);
  
  	/**
  	 * For a given stock of preferred type ticker price, par value and fixed dividend, calculates the dividend yield.
  	 * @param  tickerPrice   the stock value ticker price
  	 * @param  parValue      the stock value per share
  	 * @param  fixedDividend the fixed dividend of the stock
  	 * @return the preferred stock dividend yield
  	 */
    double calculateDividendYieldPreferred(double tickerPrice, double parValue, double fixedDividend);
  
  	/**
  	 * For a given stock ticker price and dividend, calculates the P/E ratio.
  	 * @param  lastDividend the last dividend of the stock
  	 * @param  tickerPrice  the stock value ticker price
  	 * @return the stock P/E ratio
  	 */
    double calculatePeRatio(double tickerPrice, double lastDividend);
    
  	/**
  	 * For a given array of trades prices, calculates the geometric mean.
  	 * @param  tradesPrices the array of trades prices
  	 * @return the geometric mean
  	 */
    double calculateGeometricMean(double... tradesPrices);

   /**
  	 * For a given array of trades prices and matching indices array of trades quantities,
  	 * calculates the stock price.
  	 * @param  tradesPrices     the array of trades prices
  	 * @param  tradesQuantities the array of trades quantities
  	 * @throws SimpleStockTradingException
  	 * @return the stock price
  	 */
    double calculateStockPrice(double[] tradesPrices, double[] tradesQuantities) throws SimpleStockTradingException;
    
    
    /**
     * Calculates stock price for a given list of trades.
     * @param trades the list of trades
     * @throws SimpleStockTradingException
     */
    double calculateVolumeWeightedStockPrice(List<TradingOrder> trades) throws SimpleStockTradingException;

    
  
    /**
     * Calculates all shares indexes for a given list of stocks.
     * @param stocks the list of stocks
     */
    double calculateGbceShares(List<Stock> stocks); 
    
    
}