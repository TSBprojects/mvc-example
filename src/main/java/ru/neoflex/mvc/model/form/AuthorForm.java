package ru.neoflex.mvc.model.form;

import lombok.Data;
import ru.neoflex.mvc.validation.annotation.AuthorExist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthorForm {
    private int id;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;
}
