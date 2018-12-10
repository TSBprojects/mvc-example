package ru.neoflex.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.neoflex.mvc.exception.AuthorNotFoundException;
import ru.neoflex.mvc.model.form.AuthorForm;
import ru.neoflex.mvc.service.AuthorService;

import javax.validation.Valid;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private static final String HTML_FOLDER = "author/";

    private static final String REDIRECT_ROOT_MAPPING = "redirect:/author/";

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return HTML_FOLDER + "list";
    }

    @GetMapping("{id}")
    public String getAuthor(
            @PathVariable int id,
            Model model
    ) throws Exception {
        model.addAttribute("author", authorService.get(id));
        return HTML_FOLDER + "view";
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("authorForm", new AuthorForm());
        return HTML_FOLDER + "add";
    }

    @PostMapping("/add")
    public String add(
            @Valid AuthorForm author,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return HTML_FOLDER + "add";
        }

        authorService.save(author);

        return REDIRECT_ROOT_MAPPING;
    }

    @GetMapping("{id}/edit")
    public String edit(
            @PathVariable int id,
            Model model
    ) {
        model.addAttribute("authorForm", authorService.get(id));
        return HTML_FOLDER + "add";
    }

    @PostMapping("{id}/edit")
    public String edit(
            @PathVariable int id,
            @Valid AuthorForm author,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return HTML_FOLDER + "add";
        }

        author.setId(id);
        authorService.save(author);

        return REDIRECT_ROOT_MAPPING;
    }

    @PostMapping("{id}/delete")
    public String delete(
            @PathVariable int id
    ) {
        authorService.delete(id);
        return REDIRECT_ROOT_MAPPING;
    }


//    @GetMapping("{name}")
//    public String getAuthorByName(
//            @PathVariable String name,
//            ModelMap model
//    ) throws Exception {
//
//        Author author = authorRepository.findByName(name)
//                .orElseThrow(() -> new NotFoundException("Entity not found"));
//
//        model.addAttribute("author", author);
//        return "author/view";
//    }


}
