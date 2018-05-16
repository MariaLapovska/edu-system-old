package com.edu.system.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edu.system.vo.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findById(Long id);
}