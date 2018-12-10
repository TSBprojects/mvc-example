package ru.neoflex.mvc.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.neoflex.mvc.domain.Author;
import ru.neoflex.mvc.model.InnerAuthorModel;

@Component
public class InnerAuthorConverter implements Converter<Author, InnerAuthorModel> {

    @Nullable
    @Override
    public InnerAuthorModel convert(Author author) {

        InnerAuthorModel authorModel = new InnerAuthorModel();
        authorModel.setId(author.getId());
        authorModel.setName(author.getName());

        return authorModel;
    }
}