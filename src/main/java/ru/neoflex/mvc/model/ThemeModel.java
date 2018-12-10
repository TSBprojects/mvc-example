package ru.neoflex.mvc.model;

import lombok.Data;

@Data
public class ThemeModel {

    private int id;

    private InnerAuthorModel author;

    private String name;

    private String description;

}
