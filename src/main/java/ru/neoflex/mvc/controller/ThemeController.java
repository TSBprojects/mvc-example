package ru.neoflex.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neoflex.mvc.model.form.ThemeForm;
import ru.neoflex.mvc.service.AuthorService;
import ru.neoflex.mvc.service.ThemeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/theme")
public class ThemeController {

    private static final String HTML_FOLDER = "theme/";

    private static final String REDIRECT_ROOT_MAPPING = "redirect:/theme/";

    private ThemeService themeService;
    private AuthorService authorService;
    private final ConversionService conversionService;

    @Autowired
    public ThemeController(
            ThemeService themeService,
            AuthorService authorService,
            @Qualifier("mvcConversionService") ConversionService conversionService) {
        this.themeService = themeService;
        this.authorService = authorService;
        this.conversionService = conversionService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("themes", themeService.getAll());
        return HTML_FOLDER + "list";
    }

    @GetMapping("{id}")
    public String getTheme(
            @PathVariable int id,
            Model model
    ) throws Exception {
        model.addAttribute("theme", themeService.get(id));
        return HTML_FOLDER + "view";
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("themeForm", new ThemeForm());
        return HTML_FOLDER + "add";
    }

    @PostMapping("/add")
    public String add(
            @Valid ThemeForm theme,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return HTML_FOLDER + "add";
        }

        themeService.save(theme);

        return REDIRECT_ROOT_MAPPING;
    }

    @GetMapping("{id}/edit")
    public String edit(
            @PathVariable int id,
            Model model
    ) {
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("themeForm", conversionService.convert(themeService.get(id), ThemeForm.class));
        return HTML_FOLDER + "add";
    }

    @PostMapping("{id}/edit")
    public String edit(
            @PathVariable int id,
            @Valid ThemeForm themeForm,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return HTML_FOLDER + "add";
        }

        themeForm.setId(id);
        themeService.save(themeForm);

        return REDIRECT_ROOT_MAPPING;
    }

    @PostMapping("{id}/delete")
    public String delete(
            @PathVariable int id
    ) {
        themeService.delete(id);
        return REDIRECT_ROOT_MAPPING;
    }

}
