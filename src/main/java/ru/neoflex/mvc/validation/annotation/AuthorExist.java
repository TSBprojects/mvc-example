package ru.neoflex.mvc.validation.annotation;

import ru.neoflex.mvc.validation.AuthorExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = AuthorExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorExist {

    String message() default "Author with specified id does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
