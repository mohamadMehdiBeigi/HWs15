package config;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidationUtil {

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T> void validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()){
            for (ConstraintViolation<T> violation : violations) {
                System.out.println(violation.getPropertyPath() + " : " + violation.getMessage());
            }
            throw new RuntimeException("TEST_______");

        }
    }
}
