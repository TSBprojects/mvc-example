package ru.neoflex.mvc.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.neoflex.mvc.model.ThemeModel;
import ru.neoflex.mvc.model.form.ThemeForm;

@Component
public class ThemeFormConverter implements Converter<ThemeModel, ThemeForm> {

    @Nullable
    @Override
    public ThemeForm convert(ThemeModel theme) {
        ThemeForm themeForm = new ThemeForm();

        themeForm.setId(theme.getId());
        themeForm.setName(theme.getName());
        themeForm.setAuthorId(theme.getAuthor().getId());
        themeForm.setDescription(theme.getDescription());

        return themeForm;
    }

}
