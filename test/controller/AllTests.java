package controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ValidatePhoneNumberTest.class, ValidateNameTest.class,ValidateAddressTest.class,ValidateRushOrderDate.class,ValidateRushOrderCondition.class})
public class AllTests {
	
}
