package ru.neoflex.mvc.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.neoflex.mvc.service.AuthorService;
import ru.neoflex.mvc.validation.annotation.AuthorExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class AuthorExistValidator implements ConstraintValidator<AuthorExist, Integer> {

    private final AuthorService authorService;

    @Autowired
    public AuthorExistValidator(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public boolean isValid(Integer authorId, ConstraintValidatorContext cvc) {
        return authorService.exist(authorId);
    }
}
