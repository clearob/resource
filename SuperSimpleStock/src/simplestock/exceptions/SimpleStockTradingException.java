package simplestock.exceptions;

/**
 * Class used to throw @see StockCalculator exceptions.
 */
public class SimpleStockTradingException extends Exception {

    static final long serialVersionUID = 123123123;

    /**
     * Default constructor.
     */
    public SimpleStockTradingException() {}

  	 /**
  	  * Constructor with error message.
  	  * @param message the error message
  	  */
    public SimpleStockTradingException(String message) {
        super(message);
    }
    
}
