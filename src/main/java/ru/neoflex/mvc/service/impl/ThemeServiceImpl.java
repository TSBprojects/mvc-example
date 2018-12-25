package ru.neoflex.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.neoflex.mvc.domain.Theme;
import ru.neoflex.mvc.exception.AuthorNotFoundException;
import ru.neoflex.mvc.exception.ThemeNotFoundException;
import ru.neoflex.mvc.model.ThemeModel;
import ru.neoflex.mvc.model.form.ThemeForm;
import ru.neoflex.mvc.repository.AuthorRepository;
import ru.neoflex.mvc.repository.ThemeRepository;
import ru.neoflex.mvc.service.ThemeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final AuthorRepository authorRepository;
    private final ThemeRepository themeRepository;

    private final ConversionService conversionService;

    @Autowired
    public ThemeServiceImpl(
            AuthorRepository authorRepository,
            ThemeRepository themeRepository,
            @Qualifier("mvcConversionService") ConversionService conversionService
    ) {
        this.authorRepository = authorRepository;
        this.themeRepository = themeRepository;
        this.conversionService = conversionService;
    }

    @Override
    public boolean exist(int id) {
        return themeRepository.existsById(id);
    }

    @Override
    public ThemeModel get(int id) {
        return conversionService.convert(
                themeRepository.findById(id)
                        .orElseThrow(() -> new ThemeNotFoundException(id)),
                ThemeModel.class
        );
    }

    @Override
    public List<ThemeModel> getAll() {
        return themeRepository.findAll()
                .stream()
                .map((a) -> conversionService.convert(a, ThemeModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ThemeModel save(ThemeForm themeForm) {

        int authorId = themeForm.getAuthorId();

        Theme theme = themeRepository.findById(themeForm.getId()).orElse(new Theme());
        theme.setName(themeForm.getName());
        theme.setDescription(themeForm.getDescription());
        theme.setAuthor(
                authorRepository.findById(authorId)
                        .orElseThrow(() -> new AuthorNotFoundException(authorId))
        );
        themeRepository.save(theme);

        return conversionService.convert(theme, ThemeModel.class);
    }

    @Override
    public void delete(int id) {
        if (!exist(id)) {
            throw new ThemeNotFoundException(id);
        }
        themeRepository.deleteById(id);
    }

    @Override
    public List<ThemeModel> getAllSortByTitle(){
        return themeRepository.findAll(new Sort(Sort.Direction.DESC,"name"))
                .stream()
                .map((a) -> conversionService.convert(a, ThemeModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ThemeModel> getAllBetween(int from, int to) {
        return themeRepository.findByIdBetween(from, to)
                .stream()
                .map((a) -> conversionService.convert(a, ThemeModel.class))
                .collect(Collectors.toList());
    }
}
