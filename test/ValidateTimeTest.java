
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceRushOrderController;


class ValidateTimeTest {
	
	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
		"2020-12-12, false",
		"2021-02-30, false",
		"1234567898, false",
		"2022-01-15, true",
	})

	public void test2(String date, boolean excepted) {
		boolean isValid = placeRushOrderController.validateTime(date);
		assertEquals(excepted, isValid);
	}

}
