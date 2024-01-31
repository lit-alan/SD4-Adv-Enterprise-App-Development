package sd4.com.validators.isbn_and_date;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = IsbnPublicationDateValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIsbnForPublicationDate {

    String message() default "Invalid ISBN for the given publication date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
