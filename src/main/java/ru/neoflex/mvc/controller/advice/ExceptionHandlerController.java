package ru.neoflex.mvc.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.mvc.exception.AuthorNotFoundException;
import ru.neoflex.mvc.exception.NotFoundException;
import ru.neoflex.mvc.exception.ThemeNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(
            NotFoundException ex,
            Model model
    ) {
        model.addAttribute("message", ex.getMessage());
        return "errors/404";
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String authorNotFoundExceptionHandler(
            AuthorNotFoundException ex,
            Model model
    ) {
        model.addAttribute("message", ex.getMessage());
        return "errors/500";
    }

    @ExceptionHandler(ThemeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String themeNotFoundExceptionHandler(
            ThemeNotFoundException ex,
            Model model
    ) {
        model.addAttribute("message", ex.getMessage());
        return "errors/500";
    }

}
