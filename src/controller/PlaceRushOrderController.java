package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PlaceRushOrderController extends PlaceOrderController {
	
	 public boolean validateTime(String date) {
		 
		 	if (date == null || date.trim().isEmpty()) {
		        System.out.println("date time is null");
		        return false;
		     }
		 
		 	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 	format.setLenient(false);
		 	
		 	Date dateNow = new Date();
		 	LocalDate localDate = dateNow.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		 	int year  = localDate.getYear();
		 	int month = localDate.getMonthValue();
		 	int day   = localDate.getDayOfMonth();
		 	String dateNoww = (year + "-"+ month+ "-" +day);
		 	try {
	            format.parse(date);
	            if(date.compareTo(dateNoww) < 0) {
	            	return false;
	            }
	        } catch (ParseException e) {
	        	System.out.println("exception!!!!!!!");
	            return false;
	        }
	        return true;
	    }
}
