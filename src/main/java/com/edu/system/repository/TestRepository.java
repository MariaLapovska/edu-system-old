package com.edu.system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.edu.system.vo.Article;
import com.edu.system.vo.Test;

public interface TestRepository extends CrudRepository<Test, Long> {
    List<Test> findByArticle(Article article);
}