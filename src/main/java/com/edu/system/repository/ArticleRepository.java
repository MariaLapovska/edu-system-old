package com.edu.system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.edu.system.vo.Article;
import com.edu.system.vo.Category;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    List<Article> findByCategory(Category category);
}