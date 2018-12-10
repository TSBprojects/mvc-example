package ru.neoflex.mvc.model.form;

import lombok.Data;
import ru.neoflex.mvc.validation.annotation.AuthorExist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ThemeForm {
    private int id;

    @AuthorExist
    private int authorId;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    @Size(min = 3, max = 1000)
    private String description;
}
