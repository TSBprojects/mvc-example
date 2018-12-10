package ru.neoflex.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.mvc.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByName(String name);
}
