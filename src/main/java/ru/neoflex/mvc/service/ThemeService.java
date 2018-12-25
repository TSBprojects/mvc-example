package ru.neoflex.mvc.service;

import org.springframework.data.domain.Sort;
import ru.neoflex.mvc.model.ThemeModel;
import ru.neoflex.mvc.model.form.ThemeForm;

import java.util.List;
import java.util.stream.Collectors;

public interface ThemeService extends CRUDService<ThemeModel, ThemeForm> {
    List<ThemeModel> getAllSortByTitle();
    List<ThemeModel> getAllBetween(int from, int to);
}
