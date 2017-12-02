package bval;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MyValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(X.class);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		X x = (X) target;
		for (int yi = 0; yi < x.y.length; yi++) {
			errors.pushNestedPath("y[" + yi + "]");
			for (int zi = 0; zi < x.y[yi].z.length; zi++) {
				errors.pushNestedPath("z[" + zi + "]");
				checkMin(x.y[yi].z[zi], errors, x);
				checkMax(x.y[yi].z[zi], errors, x);
				errors.popNestedPath();
			}
			errors.popNestedPath();
		}
	}
	
	private void checkMin(Z z, Errors errors, X context) {
		if (z.val < context.min) {
			errors.rejectValue("val", "must be greater than or equal to " + context.min);
		}
	}
	
	private void checkMax(Z z, Errors errors, X context) {
		if (z.val > context.max) {
			errors.rejectValue("val", "must be less than or equal to " + context.max);
		}
	}
}
