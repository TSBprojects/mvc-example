package ru.neoflex.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.neoflex.mvc.domain.Author;
import ru.neoflex.mvc.exception.AuthorNotFoundException;
import ru.neoflex.mvc.model.AuthorModel;
import ru.neoflex.mvc.model.form.AuthorForm;
import ru.neoflex.mvc.repository.AuthorRepository;
import ru.neoflex.mvc.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final ConversionService conversionService;

    @Autowired
    public AuthorServiceImpl(
            AuthorRepository authorRepository,
            @Qualifier("mvcConversionService") ConversionService conversionService
    ) {
        this.authorRepository = authorRepository;
        this.conversionService = conversionService;
    }

    @Override
    public boolean exist(int id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorModel get(int id) {
        return conversionService.convert(
                authorRepository.findById(id)
                        .orElseThrow(() -> new AuthorNotFoundException(id)),
                AuthorModel.class
        );
    }

    @Override
    public List<AuthorModel> getAll() {
        return authorRepository.findAll()
                .stream()
                .map((a) -> conversionService.convert(a, AuthorModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorModel save(AuthorForm authorForm) {
        Author author = authorRepository.findById(authorForm.getId()).orElse(new Author());
        author.setName(authorForm.getName());
        authorRepository.save(author);
        return conversionService.convert(author, AuthorModel.class);
    }

    @Override
    public void delete(int id) {
        if (!exist(id)) {
            throw new AuthorNotFoundException(id);
        }
        authorRepository.deleteById(id);
    }
}
