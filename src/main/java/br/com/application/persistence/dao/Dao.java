package br.com.application.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional <T> get(Long id);

    List<T> getAll();
    void save (T data);

    void update(T data);

    void delete(T data);
}
