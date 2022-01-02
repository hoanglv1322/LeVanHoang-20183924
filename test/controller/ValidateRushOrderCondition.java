package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateRushOrderCondition {

	private PlaceRushOrderController mPlaceRushOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		mPlaceRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		"Hanoi,true",
		"hoanghoathanhhoa,false",
		"giaiphonghanoi,true",
		"thanhhoa,false",
		",false",
	})
	void test(String address,boolean expected) {
		boolean isValided = mPlaceRushOrderController.validateRushOrderCondition(address);
		assertEquals(expected,isValided);
	}

}
