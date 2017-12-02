package bval;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, X> {
	@Override
	public boolean isValid(X x, ConstraintValidatorContext context) {
		boolean isValid = true;
		for (int yi = 0; yi < x.y.length; yi++) {
			for (int zi = 0; zi < x.y[yi].z.length; zi++) {
				if (x.y[yi].z[zi].val < x.min) {
					context
						.buildConstraintViolationWithTemplate("must be greater than or equal to " + x.min)
						.addPropertyNode("y")
						.addPropertyNode("z")
						.inIterable().atIndex(yi)
						.addPropertyNode("val")
						.inIterable().atIndex(zi)
						.addConstraintViolation()
						.disableDefaultConstraintViolation();
					isValid = false;
				}
				if (x.y[yi].z[zi].val > x.max) {
					context
						.buildConstraintViolationWithTemplate("must be less than or equal to " + x.max)
						.addPropertyNode("y")
						.addPropertyNode("z")
						.inIterable().atIndex(yi)
						.addPropertyNode("val")
						.inIterable().atIndex(zi)
						.addConstraintViolation()
						.disableDefaultConstraintViolation();
					isValid = false;
				}
			}
		}
		return isValid;
	}
}
