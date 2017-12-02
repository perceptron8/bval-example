package bvel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import bval.MyValidator;
import bval.X;
import bval.Y;
import bval.Z;

public class MyValidatorTest {
	@Test
	public void validate() {
		Z z = new Z();
		z.val = 5;
		
		Y y = new Y();
		y.z = new Z[] { z };
		
		X x = new X();
		x.y = new Y[] { y };
		x.min = 1;
		x.max = 3;
		
		Validator validator = new MyValidator();
		Errors errors = new DirectFieldBindingResult(x, "x");
		validator.validate(x, errors);
		assertEquals(1, errors.getErrorCount());
	}
}
