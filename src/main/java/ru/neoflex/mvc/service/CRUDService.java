package ru.neoflex.mvc.service;

import java.util.List;

public interface CRUDService<M, F>{

    boolean exist(int id);

    M get(int id);

    List<M> getAll();

    M save(F form);

    void delete(int id);
}

