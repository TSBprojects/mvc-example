package ru.neoflex.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.mvc.model.ThemeModel;
import ru.neoflex.mvc.service.ThemeService;

@RequestMapping("/theme/rest")
@RestController
public class ThemeRestController {

    private ThemeService themeService;

    @Autowired
    public ThemeRestController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping("{id}")
    public ThemeModel getTheme(@PathVariable int id) {
        return themeService.get(id);
    }
}
