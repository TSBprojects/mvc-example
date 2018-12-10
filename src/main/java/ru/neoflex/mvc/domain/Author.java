package ru.neoflex.mvc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "author_id")
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Theme> themes = new HashSet<>();

}

