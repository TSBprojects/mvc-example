package ru.neoflex.mvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.neoflex.mvc.domain.Author;
import ru.neoflex.mvc.model.AuthorModel;
import ru.neoflex.mvc.model.ThemeModel;

import java.util.stream.Collectors;

@Component
public class AuthorConverter implements Converter<Author, AuthorModel> {

    private ConversionService conversionService;

    @Nullable
    @Override
    public AuthorModel convert(Author author) {

        AuthorModel authorModel = new AuthorModel();
        authorModel.setId(author.getId());
        authorModel.setName(author.getName());
        authorModel.setThemes(
                author.getThemes().stream()
                        .map((a) -> conversionService.convert(a, ThemeModel.class))
                        .collect(Collectors.toSet())
        );

        return authorModel;
    }

    @Autowired
    public void setConversionService(@Lazy ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
