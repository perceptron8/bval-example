package bvel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import bval.X;
import bval.Y;
import bval.Z;

public class MyConstraintValidatorTest {
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
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<X>> violations = validator.validate(x);
		assertEquals(1, violations.size());
		
		factory.close();
	}
}
