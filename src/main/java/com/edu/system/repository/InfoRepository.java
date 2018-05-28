package com.edu.system.repository;

import org.springframework.data.repository.CrudRepository;

import com.edu.system.vo.Article;
import com.edu.system.vo.Info;

public interface InfoRepository extends CrudRepository<Info, Long> {
    Info findFirstByArticleOrderById(Article article);
}