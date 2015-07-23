package simplestock.implemented_model;

import java.util.Date;

import simplestock.interface_model.BuyOrSellEnum;
import simplestock.interface_model.Stock;
import simplestock.interface_model.TradingOrder;


/**
 * Class representing a trade.
 * @author Charles-Philippe Bernard
 * @see com.TradingOrder.shared.pojo.Trade
 */
public class TradingOrderImpl implements TradingOrder {

  	 /*
  	  * Stock.
  	  */
    private Stock stock;
  
  	 /*
  	  * Timestamp of the trade.
  	  */
  	 private Date timestamp;

    /*
  	  * Share quantity of trade.
  	  */
    private Double sharesQuantity;
  
  	 /*
  	  * Buy or sell indicator.
  	  */
    private BuyOrSellEnum buyOrSellIndicator;

    /*
  	  * Trade's price.
  	  */
    private Double price;

  	 /**
  	  * Default constructor.
  	  */
    public TradingOrderImpl() {
      
    }
  
    /**
     * Constructor with all properties.
     * @param stock
     * @param timestamp
     * @param sharesQuantity
     * @param buyOrSellIndicator
     * @param price
     */
    public TradingOrderImpl(Stock stock,
                     Date timestamp,
                     Double sharesQuantity,
                     BuyOrSellEnum buyOrSellIndicator,
                     Double price) {
      
        this.stock = stock;
        this.timestamp = timestamp;
        this.sharesQuantity = sharesQuantity;
        this.buyOrSellIndicator = buyOrSellIndicator;
        this.price = price;
    }
  
    @Override
  	 public Stock getStock() {
      
        return this.stock;
    }
  
    @Override
    public void setStock(Stock stock) {
      
        this.stock = stock;
    }
    
    @Override
  	 public Date getTimestamp() {
      
        return this.timestamp;
    }
  
    @Override
    public void setTimestamp(Date timestamp) {
      
        this.timestamp = timestamp;
    }

    @Override
    public Double getSharesQuantity() {
      
        return this.sharesQuantity;
    }
  
    @Override
    public void setSharesQuantity(Double sharesQuantity) {
      
        this.sharesQuantity = sharesQuantity;
    }

    @Override
    public BuyOrSellEnum getBuyOrSellIndicator() {
      
        return this.buyOrSellIndicator;
    }
  
    @Override
    public void setBuyOrSellIndicator(BuyOrSellEnum buyOrSellIndicator) {
      
        this.buyOrSellIndicator = buyOrSellIndicator;
    }

    @Override
    public Double getPrice() {
      
        return this.price;
    }
  
    @Override
    public void setPrice(Double price) {
      
        this.price = price;
    }

   
}
