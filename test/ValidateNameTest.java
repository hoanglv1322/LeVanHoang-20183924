
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidateNameTest {
	
	private PlaceOrderController placeOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"hoang, true",
		"hoang12, false",
		"hoang$, false",
		"hoanglevan, true",
	})

	public void test2(String name, boolean excepted) {
		boolean isValid = placeOrderController.validateName(name);
		assertEquals(excepted, isValid);
	}


}
