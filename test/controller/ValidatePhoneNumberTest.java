package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidatePhoneNumberTest {
	
	private PlaceOrderController mPlaceOrderController;
	private PlaceRushOrderController mPlaceRushOrderController;
	@BeforeEach
	void setUp() throws Exception {
		mPlaceOrderController = new PlaceOrderController();
		mPlaceRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		"0123456789,true",
		"01234,false",
		"abc123,false",
		"1234567890,false"
	})
	void test(String phone, boolean expected) {
		boolean isValided = mPlaceOrderController.validatePhoneNumber(phone);
		assertEquals(expected,isValided);
	}
	
	@ParameterizedTest
	@CsvSource({
		"0123456789,true",
		"01234,false",
		"abc123,false",
		"1234567890,false"
	})
	void testRushOrder(String phone, boolean expected) {
		boolean isValided = mPlaceRushOrderController.validatePhoneNumber(phone);
		assertEquals(expected,isValided);
	}
}
