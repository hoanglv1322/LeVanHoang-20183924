
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidateAddressTest {

	private PlaceOrderController placeOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"ngo 4 phuong mai, true",
		"ngo 4@ hai ba trung, false",
		"ngach 5 #truong @dinh, false"
	})

	public void test3(String address, boolean excepted) {
		boolean isValid = placeOrderController.validateAddress(address);
		assertEquals(excepted, isValid);
	}


}
