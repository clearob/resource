package simplestock.mainapp;

import java.lang.StringBuffer;
import java.util.Formatter;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;



import simplestock.exceptions.SimpleStockTradingException;
import simplestock.implemented_model.StockImpl;
import simplestock.implemented_model.TradingOrderImpl;
import simplestock.implemented_util.StockCalculatorUtilImpl;
import simplestock.interface_model.BuyOrSellEnum;
import simplestock.interface_model.Stock;
import simplestock.interface_model.StockTypeEnum;
import simplestock.interface_model.TradingOrder;
import simplestock.interface_util.StockCalculatorUtil;




public class SimpleStockTrader 
{
	static StockCalculatorUtil stockCalculatorUtil =  new StockCalculatorUtilImpl();

	public static void main( String[] args ) throws SimpleStockTradingException 
	{


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

		Stock stock = null;
		TradingOrder trade = null;
		List<TradingOrder> trades = null;

		Random rand = new Random();

		int numberTrades = 100;

		Map<Stock, List<TradingOrder>> map = new HashMap<Stock, List<TradingOrder>>();

		for(int i = 0; i < numberTrades; i++) {

			stock = stocks.get(rand.nextInt(stocks.size() - 1));

			trades = map.get(stock);

			if (trades == null) {

				trades = new ArrayList<TradingOrder>();

				map.put(stock, trades);
			}

			trade = new TradingOrderImpl(stock, new Date(), (rand.nextDouble()*1000d + 50d), rand.nextBoolean()?BuyOrSellEnum.BUY:BuyOrSellEnum.SELL, (rand.nextDouble()*1000d + 50d));

			trades.add(trade);
		}

		displayStocks(stocks,map);
	}	


	private static void displayStocks(List<Stock> stocks,Map<Stock, List<TradingOrder>> map) throws SimpleStockTradingException 
	{
		StringBuffer buff = null;



		System.out.println(" Symbol  Dividend yield  P/E ratio   Volume Weighted Stock price ");
		System.out.println(" ------  --------------- ----------  --------------------------- ");


		List<TradingOrder> trades;

		
			for(Stock stockIterator : stocks) {

				trades = map.get(stockIterator);

				buff = new StringBuffer();


				double yeld = stockIterator.getStockType().equals(StockTypeEnum.COMMON)?
						      stockCalculatorUtil.calculateDividendYieldCommon(
							  stockIterator.getParValue(), stockIterator.getLastDividend()):
							  stockCalculatorUtil.calculateDividendYieldPreferred(
							  stockIterator.getParValue(), stockIterator.getParValue(), stockIterator.getFixedDividend());



								buff.append(String.format("%6s ", stockIterator.getStockSymbol())+
										String.format("  %14.2f ",yeld) +
										String.format("  %9.2f ", stockCalculatorUtil.calculatePeRatio(stockIterator.getParValue(), stockIterator.getLastDividend()))+
										String.format(" %27.2f  ", stockCalculatorUtil.calculateVolumeWeightedStockPrice(trades)));



								System.out.println(buff);

			}

			System.out.println();
			System.out.println();
			System.out.println("GBCE shares: "+stockCalculatorUtil.calculateGbceShares(stocks));


		
	}
}



