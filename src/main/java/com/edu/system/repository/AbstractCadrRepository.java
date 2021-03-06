package com.edu.system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.edu.system.vo.AbstractCadr;
import com.edu.system.vo.Article;

public interface AbstractCadrRepository extends CrudRepository<AbstractCadr, Long> {
    List<AbstractCadr> findByArticleOrderById(Article article);
}