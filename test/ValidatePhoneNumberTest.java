
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidatePhoneNumberTest {
	
	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"0123456780, true",
		"0123457, false",
		"012345678941, false"
	})

	public void test1(String phone, boolean excepted) {
		boolean isValid = placeOrderController.validatePhoneNumber(phone);
		
		assertEquals(excepted, isValid);
	}

}
