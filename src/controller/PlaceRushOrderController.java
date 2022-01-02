package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.exception.InvalidDeliveryInfoException;
import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.media.Media;
import entity.order.Order;
import entity.order.OrderMedia;

public class PlaceRushOrderController extends BaseController{
	/**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());
    
    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeRushOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }
    
    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }
    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }
    
    public void confirmInvoice() {
    	
    }
    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	
    }
    
    
    /**
     * The method check if the Media is available or not
     * @param media
     */
    public void checkAvailiabilityOfProduct(Media media) {
    	
    } 
    /**
     * The method calculate rush order delivery method
     * @param condition
     * @throws InterruptedException
     * @throws IOException
     */
    public void calculateRushOrderDeliveryMethod() throws InterruptedException,IOException{
    	
    }
    
    /**
     * This method validate if the phonenumber user type in is valid or not
     * @param phoneNumber
     * @return
     */
    public boolean validatePhoneNumber(String phoneNumber) {
    	//check the phoneNumber has 10 digits
    	if(phoneNumber.length()!=10) return false;
    	//check if phoneNumber doesn't start with 0
    	if(phoneNumber.charAt(0)!='0') return false;
    	//check the phoneNumber contains only number
    	try {
    		Integer.parseInt(phoneNumber);
    	}catch(NumberFormatException e){
    		return false;
    	}
    	return true;
    }
    
    /**
     * This method validate if the name user type in is valid or not 
     * @param name
     * @return
     */
    public boolean validateName(String name) {
    	//special case of under 
    	if (name == null || name=="") {
            return false;
        }
    	//check if name contains only a-z,A-Z
    	Pattern validator = Pattern.compile("^[a-zA-Z\s]*$");
        Matcher m = validator.matcher(name);
        return m.matches();
    }
    
    /**
     * This method validate if the address user type in is valid or not
     * @param address
     * @return
     */
    public boolean validateAddress(String address) {
    	//Check if address is null or empty string
    	if(address==null || address =="") {
    		return false;
    	}
    	Pattern validator = Pattern.compile("^[a-zA-Z0-9\s]*$");
    	Matcher m = validator.matcher(address);
    	return m.matches();
    }
    
    /**
     * This method check if the date user type in is valid or not
     * @param date the date user type in
     * @return
     */
    public boolean validateRushOrderDate(String date) {
    	if(date==null||date=="") {
    		return false;
    	}
    	try {
    		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    
    /**
     * This method validate rush order condition, check if address is in Hanoi or not
     * @param address
     * @return
     */
    public boolean validateRushOrderCondition(String address){
    	//Check if address is null or empty string
    	if(address==null || address =="") {
    		return false;
    	}
    	if(address.contains("hanoi")||address.contains("Hanoi")||address.contains("Ha Noi")
    			||address.contains("ha noi")||address.contains("Ha noi"))
    		return true;
    	return false;
    }
    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
    /**
     * This method process rush order delivery form
     * @throws InvalidDeliveryInfoException
     */
    public void processRushOrderDeliveryForm() throws InvalidDeliveryInfoException{
    	
    }
}
