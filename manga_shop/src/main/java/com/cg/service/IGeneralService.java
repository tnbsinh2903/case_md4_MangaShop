package com.cg.service;

 import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGeneralService<T> {

    Optional<T> findById(Long id);

    Iterable<T> findAll();

    T save(T t);

    void remove(Long id);


}
