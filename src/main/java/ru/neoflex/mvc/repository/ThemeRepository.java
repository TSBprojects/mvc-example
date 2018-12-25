package ru.neoflex.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.mvc.domain.Theme;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Integer>{

    List<Theme> findByIdBetween(int from, int to);
}
