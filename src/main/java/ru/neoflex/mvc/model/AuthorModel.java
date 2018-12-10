package ru.neoflex.mvc.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorModel extends InnerAuthorModel {

    private Set<ThemeModel> themes = new HashSet<>();

}
