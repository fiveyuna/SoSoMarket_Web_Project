package com.example.jpetstore.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.example.jpetstore.controller.AuctionForm;
import com.example.jpetstore.domain.Auction;
/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Component
public class AuctionFormValidator implements Validator {
	
	public boolean supports(Class<?> clazz) {
		return Auction.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		AuctionForm auctionForm = (AuctionForm)obj; 
		Auction auction = auctionForm.getAuction();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.product.title", "PRODUCT_TITLE_REQUIRED");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.product.description", "DESCRIPTION_REQUIRED");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.product.shipping", "SHIPPING_REQUIRED");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "auction.deadLine", "DEADLINE_REQUIRED");
	    
	    if (auction.getStartPrice() < 1000) {
        	errors.rejectValue("auction.startPrice", "PRICE_TOO_LOW");
        }
	    
	    
	    if (errors.hasFieldErrors() && !errors.hasGlobalErrors()) {
	    	errors.reject("ERRORS_OCCURED");
	    }
	}
}