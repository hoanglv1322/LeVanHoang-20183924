package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateAddressTest {
	
	private PlaceOrderController mPlaceOrderController;
	private PlaceRushOrderController mPlaceRushOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		mPlaceOrderController = new PlaceOrderController();
		mPlaceRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		"Thanhhoa,true",
		"01234,true",
		"Thanh hoa,true",
		"Nha 30/Ngo 20/345 Giai Phong/Ha Noi,false",
		"Hai Ba Trung /Ha Noi,false",
		",false",
	})
	void test(String address,boolean expected) {
		boolean isValided = mPlaceOrderController.validateAddress(address);
		assertEquals(expected,isValided);
	}
	@ParameterizedTest
	@CsvSource({
		"Thanhhoa,true",
		"01234,true",
		"Thanh hoa,true",
		"Nha 30/Ngo 20/345 Giai Phong/Ha Noi,false",
		"Hai Ba Trung /Ha Noi,false",
		",false",
	})
	void testRushOrder(String address,boolean expected) {
		boolean isValided = mPlaceRushOrderController.validateAddress(address);
		assertEquals(expected,isValided);
	}
}
