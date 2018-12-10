package ru.neoflex.mvc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Theme {

    @Id
    @GeneratedValue
    @Field(name = "theme_id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Author author;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

}
