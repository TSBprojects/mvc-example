package ru.neoflex.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.mvc.domain.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {

}
