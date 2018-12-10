package ru.neoflex.mvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.neoflex.mvc.domain.Theme;
import ru.neoflex.mvc.model.InnerAuthorModel;
import ru.neoflex.mvc.model.ThemeModel;

@Component
public class ThemeConverter implements Converter<Theme, ThemeModel> {

    private ConversionService conversionService;

    @Nullable
    @Override
    public ThemeModel convert(Theme theme) {
        ThemeModel themeModel = new ThemeModel();

        themeModel.setId(theme.getId());
        themeModel.setName(theme.getName());
        themeModel.setAuthor(conversionService.convert(theme.getAuthor(), InnerAuthorModel.class));
        themeModel.setDescription(theme.getDescription());

        return themeModel;
    }


    @Autowired
    public void setConversionService(@Lazy ConversionService conversionService) {
        this.conversionService = conversionService;
    }
}
